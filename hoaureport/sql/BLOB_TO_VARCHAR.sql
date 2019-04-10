CREATE OR REPLACE FUNCTION BLOB_TO_VARCHAR (Blob_In In Blob)
  Return Varchar2 Is
  V_Varchar Varchar2(4000);
  V_Start   Pls_Integer := 1;
  V_Buffer  Pls_Integer := 4000;
Begin
  If Dbms_Lob.Getlength(Blob_In) Is Null Then
    Return '';
  End If;
  For I In 1 .. Ceil(Dbms_Lob.Getlength(Blob_In) / V_Buffer) Loop
    --当转换出来的字符串乱码时，可尝试用注释掉的函数
    --V_Varchar := Utl_Raw.Cast_To_Varchar2(Utl_Raw.Convert(Dbms_Lob.Substr(Blob_In, V_Buffer, V_Start),'SIMPLIFIED CHINESE_CHINA.ZHS16GBK', 'AMERICAN_THE NETHERLANDS.UTF8'));
    V_Varchar := Utl_Raw.Cast_To_Varchar2(Dbms_Lob.Substr(Blob_In,
                                                          V_Buffer,
                                                          V_Start));
    V_Start   := V_Start + V_Buffer;
  End Loop;
  Return V_Varchar;
End Blob_To_Varchar;
