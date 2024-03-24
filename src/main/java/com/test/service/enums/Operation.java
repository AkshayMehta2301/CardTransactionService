package com.test.service.enums;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
    NORMAL_PURCHASE, PURCHASE_WITH_INSTALLMENTS, WITHDRAWAL, CREDIT_VOUCHER;

    private static Map<Operation, Operation> strategyMap;

    static {
        strategyMap = new HashMap<>();
        strategyMap.put(NORMAL_PURCHASE, WITHDRAWAL);
        strategyMap.put(PURCHASE_WITH_INSTALLMENTS, WITHDRAWAL);
        strategyMap.put(WITHDRAWAL, WITHDRAWAL);
        strategyMap.put(CREDIT_VOUCHER, CREDIT_VOUCHER);
    }

    public static Operation getMappedStrategy(Operation result) {
        return strategyMap.get(result);
    }
}
