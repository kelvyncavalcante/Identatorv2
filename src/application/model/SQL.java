package application.model;

public class SQL {
	
	String instructionSelect = "";
	String instructionInsert = "";
	String instructionUpdate = "";
	String instructionDelete = "";
	
	
	public String getInstructionSelect() {
		
		return  "@Override\r\n" + 
				"private void nomeDoMetodo( PropertiesData pd )\r\n" + 
				"    {\r\n" + 
				"        s_log.trace( \"Entrada\" );\r\n" + 
				"        \r\n" + 
				"        if( s_log.isDebugEnabled( ) )\r\n" + 
				"            s_log.debug( \"\\tpd    = \" + pd );\r\n" + 
				"        \r\n" + 
				"        //InputPortfolioData ipd = ( InputPortfolioData )pd.getValue( s_strIp );\r\n" + 
				"        \r\n" + 
				"        Connection con = empresta( );\r\n" + 
				"        try\r\n" + 
				"        {\r\n" + 
				"            DrivePreparedStatement dps = new DrivePreparedStatement( con,\r\n" + 
				"                \"SELECT x.AAA, y.BBB, x.CCC \" +\r\n" + 
				"                  \"FROM TabelaX x, TabelaY y \" +\r\n" + 
				"                 \"WHERE x.AAA = ? \" +\r\n" + 
				"                   \"AND y.BBB = x.CCC \" +\r\n" + 
				"              \"ORDER BY x.AAA \" );\r\n" + 
				"            \r\n" + 
				"            dps.setInt( 1, m_VariavelParametro );\r\n" + 
				"            \r\n" + 
				"            DriveResultSet drs = new DriveResultSet( dps );\r\n" + 
				"       \r\n" + 
				"            if( drs.next( ) )\r\n" + 
				"            {\r\n" + 
				"                m_nVaravelRecuperada1   = drs.getInt   ( \"x.AAA\"     );\r\n" +
				"                m_nVaravelRecuperada2   = drs.getInt   ( \"y.BBB\"     );\r\n" + 
				"                m_nVaravelRecuperada3   = drs.getInt   ( \"x.CCC\"     );\r\n" + 
				"                \r\n" + 
				"            }\r\n" + 
				"            \r\n" + 
				"            drs.close( );\r\n" + 
				"            dps.close( );\r\n" + 
				"        }\r\n" + 
				"        catch( Exception e )\r\n" + 
				"        {\r\n" + 
				"            erro( \"Erro em nomeDoMetodo()\", e );\r\n" + 
				"        }\r\n" + 
				"        devolve( con );\r\n" + 
				"        \r\n" + 
				"        s_log.trace( \"Saida\" );\r\n" + 
				"    }" ;
	}

	public String getInstructionUpdate() {
		return  "@Override\r\n" + 
				"public SvcStatus NomeDoMetodo()( Object objKey, PropertiesData pd )\r\n" + 
				"	{\r\n" + 
				"	    SvcStatus   ss   = new SvcStatus( );\r\n" + 
				"	    Row        row   = new Row      ( );\r\n" + 
				"	    Connection con   = empresta     ( );\r\n" + 
				"	    \r\n" + 
				"	    try\r\n" + 
				"	    {\r\n" + 
				"	        DrivePreparedStatement dps = new DrivePreparedStatement( con,\r\n" + 
				"	            \" UPDATE TABELA      \\n\" +\r\n" + 
				"	            \" SET    Campo1    = ?, \\n\" +\r\n" + 
				"	                   \" Campo1    = ?, \\n\" +\r\n" + 
				"	                   \" Campo2    = ?, \\n\" +\r\n" + 
				"	            \" WHERE  CampoPK   = ?    \");\r\n" + 
				"	        \r\n" + 
				"	        int nPrm = 0;\r\n" + 
				"	        \r\n" + 
				"	        dps.setInt    ( ++nPrm, pd.getIntValue    ( s_strIiCarteira    			       ) );\r\n" + 
				"	        dps.setString ( ++nPrm, pd.getComboboxText(s_srtCbTipo                         ) );\r\n" + 
				"	        dps.setDate   ( ++nPrm, new JDDate        ( pd.getDateValue( s_srtIdtDataMov ) ) );\r\n" + 
				"	        \r\n" + 
				"	        dps.executeUpdate( );\r\n" + 
				"	        dps.close( );\r\n" + 
				"	 \r\n" + 
				"	        row.setKey   ( new Integer    ( pd.getIntValue    ( s_strIiId       )                                ) );                                                                             \r\n" + 
				"	        row.addColumn( new ColumnValue( pd.getIntValue    ( s_strIiCarteira )                                ) );\r\n" + 
				"	        row.addColumn( new ColumnValue( pd.getComboboxText( s_srtCbTipo     )                                ) );\r\n" + 
				"	        row.addColumn( new ColumnValue( pd.getDateValue   ( s_srtIdtDataMov ), ColumnValue.s_eDateFormat_DMY ) );\r\n" + 
				"	 \r\n" + 
				"	        ss.setRow( row );           \r\n" + 
				"	    }\r\n" + 
				"	    catch( Exception e )\r\n" + 
				"	    {\r\n" + 
				"	        ss.setError( strML( \"Erro na Alteração!¶Update Error!\" ), e.getMessage( ) );\r\n" + 
				"	        s_log.error( \"Erro no método NomeDoMetodo()( )\", e );\r\n" + 
				"	    }       \r\n" + 
				"	    devolve( con );\r\n" + 
				"	 \r\n" + 
				"	    return ss;\r\n" + 
				"	 }" ;
	}
	
	public String getInstructionInsert() {
		return  "@Override\r\n" + 
				"public SvcStatus nomeDoMetodo( PropertiesData pd  )\r\n" + 
				"	{\r\n" + 
				"		\r\n" + 
				"		 s_log.trace( \"Entrada\" );\r\n" + 
				"		 \r\n" + 
				"		    if( s_log.isDebugEnabled( ) )\r\n" + 
				"		        s_log.debug( \"\\tpd     = \"     + pd );\r\n" + 
				"		\r\n" + 
				"	    SvcStatus  ss    = new SvcStatus( );\r\n" + 
				"	    Row       row    = new Row( );\r\n" + 
				"	         \r\n" + 
				"	    Connection con   = empresta( );\r\n" + 
				"	    \r\n" + 
				"	    try\r\n" + 
				"	    {\r\n" + 
				"	    	\r\n" + 
				"	        DrivePreparedStatement dps = new DrivePreparedStatement( con,\r\n" + 
				"	            \" INSERT INTO TABELA( Campo1, Campo2, Campo3, \\n\" +\r\n" + 
				"	            \" VALUES ( ?, ?, ? ) \" );\r\n" + 
				"	        \r\n" + 
				"	        int nPrm = 0;\r\n" + 
				"	        \r\n" + 
				"	        dps.setInt( ++nPrm,  pd.getIntValue( s_strIiId       ) );\r\n" + 
				"	        dps.setInt( ++nPrm,  pd.getIntValue( s_strIiCarteira ) );\r\n" + 
				"	        dps.setInt( ++nPrm,  pd.getIntValue( s_srtIiPlayer   ) );\r\n" + 
				"	        \r\n" + 
				"	        dps.executeUpdate( );\r\n" + 
				"	 \r\n" + 
				"	        row.setKey   ( new Integer    ( pd.getIntValue( s_strIiId       ) ) );\r\n" + 
				"	        row.addColumn( new ColumnValue( pd.getIntValue( s_strIiCarteira ) ) );\r\n" + 
				"	        row.addColumn( new ColumnValue( pd.getIntValue( s_srtIiPlayer   ) ) );\r\n" + 
				"	        \r\n" + 
				"	        \r\n" + 
				"	        if( s_log.isDebugEnabled( ) )\r\n" + 
				"	            s_log.debug( \"\\t@return = \" + ss );\r\n" + 
				"	        \r\n" + 
				"	        ss.setRow( row );\r\n" + 
				"	    }\r\n" + 
				"	    catch( Exception e )\r\n" + 
				"	    {\r\n" + 
				"	        ss.setError( strML( \"Erro na Inclusão!¶Inclusion Error!\" ), e.getMessage( ) );\r\n" + 
				"	        s_log.error( \"Erro no método nomeDoMetodo()\", e );\r\n" + 
				"	    }\r\n" + 
				"	    devolve( con );\r\n" + 
				"	 \r\n" + 
				"	 \r\n" + 
				"	    return ss;\r\n" + 
				"	}" ;

	}
	
	public String getInstructionDelete() {
		return  "@Override\r\n" + 
				"    public SvcStatus NomeDoMetodo( Object objKey )\r\n" + 
				"    {\r\n" + 
				"        if( s_log.isDebugEnabled( ) ) \r\n" + 
				"            s_log.debug( \"\\tobjKey = \" + objKey );        \r\n" + 
				"        \r\n" + 
				"        SvcStatus ss = new SvcStatus( );\r\n" + 
				"        \r\n" + 
				"        String strErro = \"Erro na exclusão do registro!¶Register exclusion error!\";\r\n" + 
				"\r\n" + 
				"        if( objKey == null || !(objKey instanceof Integer) )\r\n" + 
				"        {\r\n" + 
				"            ss.setError( strML( strErro ),\r\n" + 
				"                         strML( \"Erro na Coleta da Chave Primária!¶Error Getting Primary Key!\" ) );\r\n" + 
				"            \r\n" + 
				"            if( s_log.isDebugEnabled( ) ) \r\n" + 
				"                s_log.debug( \"\\t@return = \" + ss );              \r\n" + 
				"            \r\n" + 
				"            return ss;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        Connection con = empresta( );\r\n" + 
				"        try\r\n" + 
				"        {\r\n" + 
				"            DrivePreparedStatement dps = new DrivePreparedStatement( con, \r\n" + 
				"                \" DELETE FROM TABELA \\n\" +\r\n" + 
				"                  \" WHERE campoPK = ?  \" );\r\n" + 
				"            \r\n" + 
				"            dps.setInt( 1, (Integer)objKey );\r\n" + 
				"    \r\n" + 
				"            if( s_log.isDebugEnabled( ) ) \r\n" + 
				"                s_log.debug( \"\\tdps = \" + dps );\r\n" + 
				"            \r\n" + 
				"            dps.executeUpdate( );\r\n" + 
				"            \r\n" + 
				"            dps.close( );\r\n" + 
				"        }\r\n" + 
				"        catch( Exception e )\r\n" + 
				"        {\r\n" + 
				"            ss.setError( strML( strErro ) );\r\n" + 
				"        \r\n" + 
				"            s_log.error( \"NomeDoMetodo\", e );\r\n" + 
				"        }\r\n" + 
				"        devolve( con );\r\n" + 
				"        \r\n" + 
				"        ss.setRefreshListView( true );\r\n" + 
				"        \r\n" + 
				"        if( s_log.isDebugEnabled( ) ) \r\n" + 
				"            s_log.debug( \"\\t@return = \" + ss );\r\n" + 
				"        \r\n" + 
				"        return ss;\r\n" + 
				"    }" ;

	}
}
