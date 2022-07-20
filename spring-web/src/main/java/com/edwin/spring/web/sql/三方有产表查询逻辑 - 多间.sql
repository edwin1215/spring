SELECT *
FROM default.dw_order_three_rsync_all_info_v2_orc
WHERE channel = 'ctrip'
  AND dt = '%(DATE)s'
  AND to_date(checkout_date) >= '%(FORMAT_DATE)s'
  -- AND coalesce(distributer, '') = 'ctrip'
  AND is_package = 0 -- 不含打包单
  AND extend_info['COUNTRY'] = '中国'
  AND extend_info['PROVINCE'] NOT IN ('香港', '澳门', '台湾')
  -- AND hotel_seq <> partner_hotel_seq
  AND hotel_seq NOT LIKE 'test_only%'
  AND room_num > 1   -- 多间
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