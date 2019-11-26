package com.ccut.ebusiness.module.tool.idutil;

public class IDTest {
    private final static String TERMINAL_SEQUENCE = "TERMINAL_SEQUENCE";//对应数据库表sequence_name 字段

    private IdUtilsMapper idUtilsMapper;
    public void add(){
        //酒店ID
        String hotelId = "";
        Long sequence = idUtilsMapper.getValue(TERMINAL_SEQUENCE);
        String tiId = IdUtils.genTerminalId(hotelId, sequence);
    }


}
