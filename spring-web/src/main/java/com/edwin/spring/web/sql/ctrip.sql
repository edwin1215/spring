## CEQ 对 齐 口 径 ， 已 与 张 涵 同 步 修 改 ， app,
## 去 分 销 ， 去 打 包 赫 丽 丹 --离店口径
SELECT
    a.hotel_grade1,
    a.order_date,
    SUM(room_night) AS `Q间夜`,
    SUM(chushifanhou) AS `Q收益`,
    SUM(gmv) AS gmvq,
    SUM(room_nightc) AS `c间夜`,
    SUM(yongjinc) AS yongjinc,
    SUM(gmvc) AS gmvc,
    SUM(room_nighte) AS `e间夜`,
    SUM(yongjine) AS yongjine,
    SUM(gmve) AS gmve,
    SUM(final_payamount_price) AS `q用户实付`
FROM
    (
        SELECT
            report_date AS order_date,
            v3.hotel_grade1,
            SUM(
                    CASE WHEN guarantee_noshow_type = 'f' THEN room_night / date_diff ELSE room_night END
                ) AS room_night,
            SUM(
                    CASE WHEN buyout_type IN ('免费房', '免费房2') THEN final_room_fee - final_subtract - final_cashback ELSE final_room_fee END
                ) AS room_fee,
            --原始房费
            SUM(gmv) AS gmv,
            SUM(
                    CASE WHEN buyout_type IN ('免费房', '免费房2') THEN 0 ELSE final_commission END
                ) AS fanqianyongjin,
            --`返前佣金`
            SUM(
                    CASE WHEN buyout_type IN ('免费房', '免费房2') THEN 0 ELSE final_room_fee - final_commission END
                ) AS cuhoudijia,
            --`商促后底价`
            SUM(
                    CASE WHEN buyout_type IN ('免费房', '免费房2') THEN 0 ELSE final_commission - final_subtract - final_cashback + final_thirdpart_subtract + final_thirdpart_cashback END
                ) AS chushifanhou,
            --`初始返后佣金`
            SUM(final_payamount_price) AS final_payamount_price
        FROM
            (
                SELECT
                    order_no,
                    hotel_seq,
                    order_date,
                    order_time,
                    checkout_date AS report_date,
                    supplier_code,
                    hotel_grade,
                    device_id,
                    buyout_type,
                    room_night,
                    is_hours_room AS is_hour,
                    CASE WHEN pay_type = 'CASH'
                        AND is_guarantee = 1
                        AND order_status = 'NO_SHOW'
                        AND guarantee_type = '首晚' THEN 'f' WHEN pay_type = 'CASH'
                        AND is_guarantee = 1
                        AND order_status = 'NO_SHOW'
                        AND guarantee_type = '全额' THEN 'a' ELSE NULL END guarantee_noshow_type,
                    --未入住情况下首晚间夜需要计算因为会扣钱
                    datediff(checkout_date, checkin_date) AS date_diff,
                    CASE WHEN user_info ['app_channel_id'] = 'smart_app' THEN 'wechat' ELSE coalesce(
                            terminal_channel_type, distributor_system
                        ) END AS terminal_type ---区分用户终端类型
                FROM
                    dw_order_info_prep_v3
                WHERE
                        dt = '%(DATE)s'
                  AND (
                        (
                                    checkout_date >= '2022-01-31'
                                AND checkout_date <= '2022-02-06'
                            )
                        OR (
                                    checkout_date >= '2021-02-11'
                                AND checkout_date <= '2021-02-17'
                            )
                        OR (
                                    checkout_date >= '2020-01-24'
                                AND checkout_date <= '2020-01-30'
                            )
                        OR (
                                    checkout_date >= '2019-02-04'
                                AND checkout_date <= '2019-02-10'
                            )
                    )
                  AND (
                        (
                                    pay_type = 'PROXY'
                                AND order_status IN (
                                                     'CHECKED_OUT', 'CHECKED_IN', 'CONFIRMED'
                                )
                            )
                        OR (
                                    pay_type = 'CASH'
                                AND is_guarantee = 1
                                AND order_status IN (
                                                     'CHECKED_OUT', 'CHECKED_IN', 'NO_SHOW',
                                                     'CONFIRMED'
                                )
                            )
                        OR (
                                    pay_type = 'CASH'
                                AND is_guarantee = 0
                                AND order_status IN (
                                                     'CHECKED_OUT', 'CHECKED_IN', 'CONFIRMED'
                                )
                            )
                    )
                  AND is_international = 0
                  AND province_name NOT IN ('香港', '澳门', '台湾')
                  AND (
                            third_distributor_channel NOT IN ('Q2C', 'Q2E')
                        OR third_distributor_channel IS NULL
                    )
                  AND coalesce(distributor_id, '') NOT IN (
                                                           '16262', '170', '1545', '8147', '17303',
                                                           '17528', '17390', '17720', '16106'
                    ) --不含打包单
                  AND terminal_channel_type IN ('app')
            ) order_info
                LEFT JOIN (
                SELECT
                    order_no,
                    final_room_fee,
                    final_gmv AS gmv,
                    final_commission,
                    predict_cashback_q,
                    final_cashback,
                    final_subtract,
                    final_thirdpart_subtract,
                    final_benefit_subtract,
                    obp,
                    coalesce(
                            final_subtract_detail ['RoomFee_Subtract_Marketing'],
                            0
                        ) - coalesce(
                            final_subtract_detail ['20190821170921999067'],
                            0
                        ) AS pingtaiyouhui,
                    final_cashback_detail,
                    real_cashback,
                    final_commission_subtract,
                    coalesce(
                            final_cashback_detail ['CashBack_Normal'],
                            0
                        ) AS yongjinfanxian,
                    final_thirdpart_cashback,
                    final_subtract_detail,
                    coalesce(
                            final_cashback_detail ['CashBack_Marketing'],
                            0
                        ) AS pingtaifanxian,
                    final_payamount_price,
                    CASE WHEN final_commission - final_cashback * 0.8 - final_subtract + final_thirdpart_subtract < 0 THEN 1 ELSE 0 END AS type
                FROM
                    dw_order_fund_v3
                WHERE
                        dt = '%(DATE)s'
            ) fund ON order_info.order_no = fund.order_no
                LEFT JOIN (
                SELECT
                    DISTINCT hotel_seq,
                             hotel_grade,
                             city_name,
                             CASE WHEN hotel_grade <= 2
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '低星' WHEN hotel_grade = 3
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '中星' WHEN hotel_grade > 3
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '高星' WHEN nvl(hotel_group_name, 0) IN (
                                                                                   '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                                   '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                 ) THEN '连锁' END AS hotel_grade1
                FROM
                    dim_hotel_info_v3
                WHERE
                        dt = '%(DATE)s'
            ) v3 ON order_info.hotel_seq = v3.hotel_seq
        GROUP BY
            1,
            2
    ) a
        LEFT JOIN (
        SELECT
            to_date(checkout_date) AS order_date,
            h.hotel_grade1,
            SUM(
                    CASE WHEN guarantee_noshow_type = 'f' THEN room_night / date_diff ELSE room_night END
                ) AS room_nightc,
            SUM(
                        commission - subtract - real_cashback - coalesce(extend_info ['C_Refund'], 0) - coalesce(extend_info ['H_Refund'], 0)
                ) AS yongjinc,
            SUM(
                        payamount_price - coalesce(extend_info ['C_Refund'], 0)
                ) AS gmvc
        FROM
            (
                SELECT
                    *,
                    CASE WHEN pay_type = '现付'
                        AND is_guarantee = 1
                        AND order_status = '未入住'
                        AND guarantee_type = '首晚' THEN 'f' WHEN pay_type = '现付'
                        AND is_guarantee = 1
                        AND order_status = '未入住'
                        AND guarantee_type = '全额' THEN 'a' ELSE NULL END guarantee_noshow_type,
                    datediff(checkout_date, checkin_date) AS date_diff,
                    CASE WHEN distributer = 'other' THEN 'other' WHEN distributer = 'ctrip'
                        AND terminal_channel_type = 'app' THEN 'app' WHEN distributer = 'ctrip'
                        AND terminal_channel_type = 'online' THEN 'www' WHEN distributer = 'ctrip'
                        AND terminal_channel_type = 'h5' THEN 'touch' WHEN distributer = 'ctrip'
                        AND terminal_channel_type = 'wechat' THEN 'wechat' ELSE terminal_channel_type END AS terminal_type
                FROM
                    default.dw_order_three_rsync_all_info_v2_orc
                WHERE
                        channel = 'ctrip'
                  AND dt = '%(DATE)s'
                  AND to_date(checkout_date) >= '2022-01-31'
                  AND coalesce(distributer, '') = 'ctrip'
                  AND is_package = 0 --不含打包单
                  AND extend_info ['COUNTRY'] = '中国'
                  AND extend_info ['PROVINCE'] NOT IN ('香港', '澳门', '台湾')
                  AND hotel_seq <> partner_hotel_seq
                  AND hotel_seq NOT LIKE 'test_only%'
                  AND (
                    (
                            (
                                        pay_type = '预付'
                                    AND order_status IN ('已经离店', '已经入住', '已经确认')
                                )
                            OR (
                                        pay_type = '现付'
                                    AND is_guarantee = 1
                                    AND order_status IN ('已经离店', '已经入住', '未入住', '已经确认')
                                )
                            OR (
                                        pay_type = '现付'
                                    AND is_guarantee = 0
                                    AND order_status IN ('已经离店', '已经入住', '已经确认')
                                )
                        )
                    )
            ) a
                LEFT JOIN (
                SELECT
                    DISTINCT hotel_seq,
                             hotel_grade,
                             city_name,
                             CASE WHEN hotel_grade <= 2
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '低星' WHEN hotel_grade = 3
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '中星' WHEN hotel_grade > 3
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '高星' WHEN nvl(hotel_group_name, 0) IN (
                                                                                   '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                                   '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                 ) THEN '连锁' END AS hotel_grade1
                FROM
                    dim_hotel_info_v3
                WHERE
                        dt = '%(DATE)s'
            ) h ON a.hotel_seq = h.hotel_seq
        WHERE
                terminal_type IN ('app')
        GROUP BY
            1,
            2
    ) ctrip ON ctrip.order_date = a.order_date
        AND ctrip.hotel_grade1 = a.hotel_grade1
        LEFT JOIN (
        SELECT
            checkout_date AS order_date,
            h.hotel_grade1,
            SUM(room_night) room_nighte,
            SUM(payamount_price) gmve,
            SUM(commission - subtract - cashback) AS yongjine,
            --初始收益
            SUM(
                        commission - subtract -(
                                get_json_object(
                                        discount_detail, "$.detail[2].amount"
                                    ) * 0.63 -- 优惠券返现
                            +(
                                     get_json_object(
                                             discount_detail, "$.detail[0].amount"
                                         ) -- 红包返现
                                     + get_json_object(
                                             discount_detail, "$.detail[5].amount"
                                         ) -- 促销返现
                                     + get_json_object(
                                             discount_detail, "$.detail[7].amount"
                                         ) -- 折扣
                                 ) * 0.7
                        )
                ) AS commission_after_pr --预估收益
        FROM
            (
                SELECT
                    *,
                    CASE WHEN distributer = 'elong' THEN 'other' WHEN distributer = 'elong'
                        AND terminal_channel_type = 'app' THEN 'app' WHEN distributer = 'elong'
                        AND terminal_channel_type = 'online' THEN 'www' WHEN distributer = 'elong'
                        AND terminal_channel_type = 'h5' THEN 'touch' WHEN distributer = 'elong'
                        AND terminal_channel_type = 'wechat' THEN 'wechat' ELSE terminal_channel_type END AS terminal_type
                FROM
                    default.dw_order_three_rsync_all_info_v2_orc
                WHERE
                        channel = 'elong'
                  AND dt = '%(DATE)s'
                  AND to_date(checkout_date) >= '2022-01-31'
                  AND (
                        (
                                    pay_type = '预付'
                                AND order_status IN ('已经离店', '已经入住', '已经确认')
                            )
                        OR (
                                    pay_type = '现付'
                                AND is_guarantee = 1
                                AND order_status IN ('已经离店', '已经入住', '未入住', '已经确认')
                            )
                        OR (
                                    pay_type = '现付'
                                AND is_guarantee = 0
                                AND order_status IN ('已经离店', '已经入住', '已经确认')
                            )
                    )
                  AND is_package = 0 --不含打包单
                  AND coalesce(distributer, '') = 'elong'
                  AND terminal_channel_type NOT IN ('HDS', 'offline', '其他')
                  AND extend_info ['country'] = '中国'
                  AND extend_info ['province'] NOT IN ('香港', '澳门', '台湾')
                  AND hotel_seq <> partner_hotel_seq
                  AND hotel_seq NOT LIKE 'test_only%'
            ) a
                LEFT JOIN (
                SELECT
                    DISTINCT hotel_seq,
                             hotel_grade,
                             city_name,
                             CASE WHEN hotel_grade <= 2
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '低星' WHEN hotel_grade = 3
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '中星' WHEN hotel_grade > 3
                                 AND nvl(hotel_group_name, 0) NOT IN (
                                                                      '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                      '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                     ) THEN '高星' WHEN nvl(hotel_group_name, 0) IN (
                                                                                   '首旅如家', '华住', '华住酒店集团', '格林豪泰酒店管理集团',
                                                                                   '锦江酒店（中国区）', '铂涛酒店集团', '锦江集团'
                                 ) THEN '连锁' END AS hotel_grade1
                FROM
                    dim_hotel_info_v3
                WHERE
                        dt = '%(DATE)s'
            ) h ON a.hotel_seq = h.hotel_seq
        GROUP BY
            1,
            2
    ) elong ON elong.order_date = a.order_date
        AND elong.hotel_grade1 = a.hotel_grade1
GROUP BY
    1,
    2