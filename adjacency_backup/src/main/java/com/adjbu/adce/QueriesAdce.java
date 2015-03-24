package com.adjbu.adce;

public class QueriesAdce {
	
	public String BtsFromBcfCode(String code){
		String BTS_query = " SELECT DISTINCT" +
				"  BTS3_O.CO_GID AS ID, BTS3_O.CO_DN AS DN, BTS3_O.CO_NAME AS NAME, BTS3_MA.BTS_SEGMENT_NAME AS SEGMENT_NAME,BTS3_MA.BTS_LOC_AREA_ID_LAC AS LAC,BTS3_MA.BTS_BICB_71 AS BCC,BTS3_MA.BTS_BICN_72 AS NCC,BTS3_MA.BTS_CELL_ID AS CI" +
				" FROM" +
				"  CTP_COMMON_OBJECTS BSC1_O" +
				"    JOIN" +
				"  CTP_COMMON_OBJECTS BCF2_O" +
				"    ON" +
				"      BCF2_O.CO_PARENT_GID = BSC1_O.CO_GID AND" +
				"      BCF2_O.CO_OC_ID = 167" +
				"    JOIN" +
				"  CTP_COMMON_OBJECTS BTS3_O" +
				"    JOIN" +
				"  C_BSC_BTS BTS3_MA" +
				"    ON" +
				"      BTS3_MA.CONF_ID = 1 AND" +
				"      BTS3_MA.OBJ_GID = BTS3_O.CO_GID" +
				"    ON" +
				"      BTS3_O.CO_PARENT_GID = BCF2_O.CO_GID AND" +
				"      BTS3_O.CO_OC_ID = 157" +
				" WHERE" +
				"  BSC1_O.CO_OC_ID = 155 AND" +
				"  BTS3_MA.BTS_SEGMENT_NAME LIKE '%"+code+"%' AND" +
				"  BTS3_O.CO_NAME = BTS3_MA.BTS_SEGMENT_NAME AND" +
				"  BTS3_O.CO_STATE = 0" ;
		
		return BTS_query;
		
	}
	public String OutgoingFromBtsDn(String DN){
		String query = " SELECT DISTINCT" +
				"   ADCE2_O.CO_DN AS DN, ADCE2_MA.ADCE_ACIL_11 AS N_LAC,ADCE2_MA.ADCE_BCCH_FREQUENCY AS N_BCCH,ADCE2_MA.ADCE_ACBB_7 AS N_BCC,ADCE2_MA.ADCE_ACBN_8 AS N_NCC,ADCE2_MA.ADCE_ACIC_10 AS N_CI" +
				" FROM" +
				"   CTP_COMMON_OBJECTS BTS1_O" +
				"     JOIN" +
				"   CTP_COMMON_OBJECTS ADCE2_O" +
				"     JOIN" +
				"   C_BSC_ADCE ADCE2_MA" +
				"     ON" +
				"       ADCE2_MA.CONF_ID = 1 AND" +
				"       ADCE2_MA.OBJ_GID = ADCE2_O.CO_GID" +
				"     ON" +
				"       ADCE2_O.CO_PARENT_GID = BTS1_O.CO_GID AND" +
				"       ADCE2_O.CO_OC_ID = 146" +
				" WHERE" +
				"   BTS1_O.CO_OC_ID = 157 AND" +
				"   BTS1_O.CO_DN ='"+DN+"' AND" +
				"   ADCE2_O.CO_STATE = 0";
		
		return query;
	}
	public String BcchTrxFromBtsDn(String DN){
		String query = "  SELECT DISTINCT" +
				"    TRX2_O.CO_GID AS ID, TRX2_O.CO_DN AS DN, TRX2_O.CO_STATE AS STATE,TRX2_MA.TRX_INI_FREQUENCY AS BCCH" +
				"  FROM" +
				"    CTP_COMMON_OBJECTS BTS1_O" +
				"      JOIN" +
				"    CTP_COMMON_OBJECTS TRX2_O" +
				"      JOIN" +
				"    C_BSC_TRX TRX2_MA" +
				"      ON" +
				"        TRX2_MA.CONF_ID = 1 AND" +
				"        TRX2_MA.OBJ_GID = TRX2_O.CO_GID" +
				"      ON" +
				"        TRX2_O.CO_PARENT_GID = BTS1_O.CO_GID AND" +
				"        TRX2_O.CO_OC_ID = 164" +
				"  WHERE" +
				"    BTS1_O.CO_OC_ID = 157 AND" +
				"    BTS1_O.CO_DN = '"+DN+"' AND" +
				"    TRX2_O.CO_STATE = 0 AND" +
				"    TRX2_MA.TRX_CH_0_TYPE = 4";
		
		
		return query;
	}

}
