select
    hotel_seq,
    terminal_type,
    checkout_date,
    count(order_no) as order_cnt,
    sum(
            case
                when guarantee_noshow_type = 'f' then room_night / date_diff
                else room_night
                end
        ) as room_night
from
    (
        select
            order_no,
            hotel_seq,
            partner_hotel_seq,
            distributer,
            checkout_date,
            room_night,
            room_fee,
            case
                when pay_type = '现付'
                    and is_guarantee = 1
                    and order_status = '未入住'
                    and guarantee_type = '首晚' then 'f'
                when pay_type = '现付'
                    and is_guarantee = 1
                    and order_status = '未入住'
                    and guarantee_type = '全额' then 'a'
                else null
                end guarantee_noshow_type,
            datediff(checkout_date, checkin_date) as date_diff,
            case
                when distributer = 'other' then 'other'
                when distributer = 'ctrip'
                    and terminal_channel_type = 'app' then 'app'
                when distributer = 'ctrip'
                    and terminal_channel_type = 'online' then 'www'
                when distributer = 'ctrip'
                    and terminal_channel_type = 'h5' then 'touch'
                when distributer = 'ctrip'
                    and terminal_channel_type = 'wechat' then 'wechat'
                else terminal_channel_type
                end as terminal_type
        from
            dw_order_three_rsync_all_info_v2
        where
                channel = 'ctrip'
          and checkout_date between '$FORMAT_DATE_BEGIN'
            and '$FORMAT_DATE_END'
          and extend_info ['COUNTRY'] = '中国'
          and extend_info ['PROVINCE'] not in ('香港', '澳门', '台湾')
          and hotel_seq <> partner_hotel_seq
          and coalesce(distributer, '') in ('ctrip')
          and is_package = 0
          and hotel_seq not like 'test_only%'
          and (
            (
                    (
                                pay_type = '预付'
                            and order_status in ('已经离店', '已经入住', '已经确认')
                        )
                    or (
                                pay_type = '现付'
                            and is_guarantee = 1
                            and order_status in ('已经离店', '已经入住', '未入住', '已经确认')
                        )
                    or (
                                pay_type = '现付'
                            and is_guarantee = 0
                            and order_status in ('已经离店', '已经入住', '已经确认')
                        )
                )
            )
    ) order
where
    terminal_type in ('www', 'touch', 'app', 'wechat')
group by
    1,
    2,
    3,
    4;



select
    *,
    case
        when pay_type = '现付'
            and is_guarantee = 1
            and order_status = '未入住'
            and guarantee_type = '首晚' then 'f'
        when pay_type = '现付'
            and is_guarantee = 1
            and order_status = '未入住'
            and guarantee_type = '全额' then 'a'
        else null
        end guarantee_noshow_type
from
    dw_order_three_rsync_all_info_v2
where
        channel = 'ctrip'
  and checkout_date between '%(FORMAT_DATE_SUB_1_M)s'
    and '%(FORMAT_DATE)s'
  and extend_info ['COUNTRY'] = '中国'
  and extend_info ['PROVINCE'] not in ('香港', '澳门', '台湾')
  and hotel_seq <> partner_hotel_seq
  and coalesce(distributer, '') in ('ctrip')
  and is_package = 0
  and hotel_seq not like 'test_only%'
  AND room_num > 1
  and (
    (
            (
                        pay_type = '预付'
                    and order_status in ('已经离店', '已经入住', '已经确认')
                )
            or (
                        pay_type = '现付'
                    and is_guarantee = 1
                    and order_status in ('已经离店', '已经入住', '未入住', '已经确认')
                )
            or (
                        pay_type = '现付'
                    and is_guarantee = 0
                    and order_status in ('已经离店', '已经入住', '已经确认')
                )
        )
    )