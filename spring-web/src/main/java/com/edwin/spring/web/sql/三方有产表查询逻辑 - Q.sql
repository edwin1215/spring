select
    report_date,
    hotel_seq,
    supplier_code,
    terminal_type,
    coalesce(is_hour,'ALL') as is_hour,
    count(order_info.order_no) as order_cnt,
    sum(case
            when guarantee_noshow_type='f' then room_night/date_diff else room_night end) as room_night,
    sum(case
            when buyout_type in ('免费房','免费房2') then final_room_fee -final_subtract-final_cashback
            else final_room_fee end) as room_fee,
        sum(final_gmv) as gmv,
        sum(case
                when buyout_type in ('免费房','免费房2') then 0
                else final_commission end) as `返前佣金`,
    sum(case
            when buyout_type in ('免费房','免费房2') then 0
            else final_room_fee-final_commission end) as `商促后底价`,
    sum(case
            when buyout_type in ('免费房','免费房2') then 0
            else final_commission-final_subtract-final_cashback+final_thirdpart_subtract+final_thirdpart_cashback end) as `初始返后佣金`,
    sum(
            case
                when buyout_type in ('免费房','免费房2') then 0
                else (final_commission - predict_cashback_q - final_subtract + final_thirdpart_subtract)end)as `预估返后佣金收益` ,
    sum(case
            when buyout_type in ('免费房','免费房2') then 0
            else final_commission-final_subtract-real_cashback+final_thirdpart_subtract+final_thirdpart_cashback end) as `实际返后佣金`,
        sum(case
                when buyout_type in ('免费房','免费房2') then 0
                else (final_room_fee-final_commission) end) as base_price,
        sum(final_payamount_price) as final_payamount_price
from

    (select
         order_no,
         hotel_seq,
         order_date,
         checkout_date as report_date,
         supplier_code,
         device_id,
         buyout_type,
         room_night,
         is_hours_room as is_hour,
         case when pay_type='CASH' and is_guarantee=1 and order_status='NO_SHOW' and guarantee_type='首晚' then 'f'
              when pay_type='CASH' and is_guarantee=1 and order_status='NO_SHOW' and guarantee_type='全额' then 'a'
              else null end guarantee_noshow_type,
             datediff(checkout_date,checkin_date) as date_diff,
             case when user_info['app_channel_id']='smart_app' then 'wechat'
                  else coalesce(terminal_channel_type,distributor_system) end as terminal_type
     FROM
         dw_order_info_v3
     WHERE
             dt='20210908'
       and order_date>='2021-08-07' and order_date<='2021-09-07'
       and (
             (pay_type='PROXY' and order_status in ('CHECKED_OUT','CHECKED_IN','CONFIRMED'))
             or (pay_type='CASH' and is_guarantee=1 and order_status in ('CHECKED_OUT','CHECKED_IN','NO_SHOW','CONFIRMED'))
             or (pay_type='CASH' and is_guarantee=0 and order_status in ('CHECKED_OUT','CHECKED_IN','CONFIRMED'))
         )
       and is_international=0 and province_name not in ('香港','澳门','台湾')
       and (third_distributor_channel not in ('Q2C','Q2E') or third_distributor_channel is null)
       and coalesce(distributor_id,'') not in ('16262','170','1545','8147','17303','17528','17390','17720','16106')
       and terminal_channel_type in ('app','touch','www')
    )order_info
        left join
    (select
         order_no,
         final_room_fee,
         final_gmv,
         final_commission,
         predict_cashback_q,
         final_cashback,
         final_subtract,
         final_thirdpart_subtract,
         real_cashback,
         final_thirdpart_cashback,
         final_payamount_price
     from
         dw_order_fund_v3
     where
             dt='20210908'
    )fund
    on order_info.order_no=fund.order_no
group by 1,2,3,4,5