/**
 * Created by NGO QUOC TRUNG MSSV:51503026
 */
//import sun.org.mozilla.javascript.internal.ast.WhileLoop;

import java.io.*;
import java.lang.reflect.Array;
import  java.util.Collection;
import  java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import  java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.lang.Comparable;

public class Main {
    public static void main(String[] args) throws IOException {
        WriteAndReadFile filefile=new WriteAndReadFile();
        String input=filefile.ReadFile("dsa_input_assign2_1.txt");
        String inp[]=input.split("\r\n");
        WriteAndReadFile red=new WriteAndReadFile();
        String input1=filefile.ReadFile("dsa_input_assign2_2.txt");
        String inp1[]=input1.split("\r\n");
        String input2=filefile.ReadFile( "dsa_input_assign2-3.txt");
        String inp2[]=input2.split("\r\n");
        String input3=filefile.ReadFile("dsa_query_assign2.txt");
        String inp3[]=input3.split("\r\n");
        String diem="([0-9]+\\.*[0-9]*)";
        String ten="[^ho_ten: ]([a-z_A-Z]+[\\s]*)+";
        Pattern te=Pattern.compile(ten);
        Pattern die=Pattern.compile(diem);

        ArrayList<ThongTinSV> sv=new ArrayList<ThongTinSV>();
        int i;
        for ( i = 0; i <=inp.length ; i=i+5)
        {
            if (i>inp.length)
            {
                break;
            }

            ThongTinSV ttsv=new ThongTinSV();
            ttsv.setMsSV(inp[i]);
            ttsv.setName(inp[i+1]);
            ttsv.setBirthday(inp[i+2]);
            ttsv.setCountry(inp[i+3]);
            sv.add(ttsv);
        }
        //int j;
        ArrayList<ThongTinHocTap> ht=new ArrayList<ThongTinHocTap>();
        for (int j = 0; j <=inp1.length ; j=j+4)
        {
            if(j>inp1.length)
            {
                break;
            }

            ThongTinHocTap ttht=new ThongTinHocTap();
            ttht.setMsMH(inp1[j]);
            ttht.setMsSV(inp1[j+1]);
            ttht.setMark(inp1[j+2]);
            ht.add(ttht);
        }
        ArrayList<ThongTinHocTap> mh=new ArrayList<ThongTinHocTap>();
        int k;
        for ( k = 0; k <=inp2.length ; k=k+3)
        {
            if (k > inp2.length)
            {
                break;
            }
            ThongTinHocTap thmh=new ThongTinHocTap();
            thmh.setMsMH(inp2[k]);
            thmh.setTenmh(inp2[k+1]);
            mh.add(thmh);
        }


        List<ThongTinHocTap> vd1=new ArrayList<ThongTinHocTap>();
        Double tb=0.000;
        int a=0;

        for(ThongTinSV SV:sv)
        {
            String monhoc="";
            ThongTinHocTap ttMH=new ThongTinHocTap();
            ttMH.setMsSV(SV.getMsSV());
            ttMH.setName(SV.getName());
            ttMH.setBirthday(SV.getBirthday());
            ttMH.setCountry(SV.getCountry());
            for (ThongTinHocTap HT:ht)
            {
                for (ThongTinHocTap MH:mh)
                {
                    String tempHTgetMsMH=HT.getMsMH().replace(" ","");
                    String tempMHgetMsMH=MH.getMsMH().replace(" ","");
                    String tempSVgetMsSV=SV.getMsSV().replace(" ","");
                    String tempHTgetMsSV=HT.getMsSV().replace(" ","");
                    if(((tempHTgetMsMH).equals(tempMHgetMsMH))&&((tempSVgetMsSV).equals(tempHTgetMsSV)))
                    {a++;
                        monhoc=monhoc+MH.getTenmh()+":"+HT.getMark()+"\r\n";
                        tb=tb+ Double.parseDouble( HT.getMark());
                    }
                }
            }
            String st=tb+"1";
            ttMH.setMark(monhoc);
            ttMH.setTrb(Double.valueOf( tb)/a);
            vd1.add(ttMH);
            a=0;tb=0.000;
        }
        String outp="";
        Collections.sort(vd1);
        for (ThongTinHocTap item:vd1)
        {
            outp=item.getMsSV()+"\r\n"+item.getName()+"\r\n"+item.getBirthday()+"\r\n"+item.getCountry()+"\r\n"+item.getMark()+"Diem trung binh:"+item.getTrb()+"\r\n"+"\r\n"+outp;
        }
        File fileinput=new File("dsa_query_assign2.txt");
        int b=0;String temp;
        List<ThongTinHocTap> queryttht=new ArrayList<ThongTinHocTap>();
        for (String item:inp3)
        {
            Matcher di=die.matcher(item);
            Matcher t=te.matcher(item);
            if (di.find())
            {
                for (ThongTinHocTap ttht:vd1)
                {
                    String temptthtgetMark=ttht.getTrb().toString().replace(" ","");
                    String tempdigroup=di.group().replace(" ","");
                    if(temptthtgetMark.equals(tempdigroup))
                    {
                        queryttht.add(ttht);
                    }
                }
            }
            if(t.find())
            {

                for (ThongTinHocTap ttht:vd1)
                {
                    String temptthtgetName=ttht.getName().replace(" ","");
                    String temptgroup=t.group().replace(" ","");
                    if(temptthtgetName.equals(temptgroup))
                    {
                        queryttht.add(ttht);
                    }
                }
            }
        }
        String query="";
        int cout=0;
        for (ThongTinHocTap item:queryttht)
        {
            cout++;
            query=item.getMsSV()+"\r\n"+item.getName()+"\r\n"+item.getBirthday()+"\r\n"+item.getCountry()+"\r\n"+item.getMark()+"Diem trung binh:"+item.getTrb()+"\r\n"+"\r\n"+query;
            filefile.setsR(query);
            filefile.WriteFile("dsa_output_query"+cout+".txt");
            query="";
        }
        //System.out.println("@@@@@"+query+"@@@@");
        filefile.setsR(outp);
        WriteAndReadFile Filequery=new WriteAndReadFile();
        filefile.WriteFile("dsa_ouput_assign2-1.txt");



    }
}
class ThongTinSV{
    private String mssv;

    public String getMsSV() {
        return mssv;
    }

    public void setMsSV(String msSV) {
        this.mssv = msSV;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private  String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    private  String birthday;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public ThongTinSV()
    {
        this.setName(null);
        this.setMsSV(null);
        this.setBirthday(null);
        this.setCountry(null);
    }
    public ThongTinSV(String setname,String setmssv,String setcountry,String setbirthday)
    {
        this.setBirthday(setbirthday);
        this.setName( setname);
        this.setMsSV(setmssv);
        this.setCountry(setcountry);
    }
}
class ThongTinHocTap extends ThongTinSV implements Comparable<ThongTinHocTap>
{
    private String msMH;

    public String getMsMH() {
        return msMH;
    }

    public void setMsMH(String msMH) {
        this.msMH = msMH;
    }
    public String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    private Double trb;

    public Double getTrb() {
        return trb;
    }

    public void setTrb(Double trb) {
        this.trb = trb;
    }
    private String tenmh;

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public ThongTinHocTap(String name,String mssv,String country,String birthday,String tenmonhoc,String diem,Double trdiem,String msmh)
    {
        super(name,mssv,country,birthday);
        this.setTenmh(tenmonhoc);
        this.setMark(diem);
        this.setTrb(trdiem);
        this.setMsMH(msmh);
    }
    public ThongTinHocTap()
    {
        super.setName(null);
        super.setBirthday(null);
        super.setCountry(null);
        super.setMsSV(null);
        this.setTenmh(null);
        this.setTrb(0.0);
        this.setMsMH(null);
        this.setMark(null);
    }
    public ThongTinHocTap(String tenmonhoc,String masomh)
    {
        this.setMsMH(masomh);
        this.setTenmh(tenmonhoc);
    }


    @Override
    public int compareTo(ThongTinHocTap other)
    {
        Double box=this.getTrb();
        String diem= box.toString();
        Double box1=other.getTrb();
        String diem1=box1.toString();
        if(this.getTrb()==other.getTrb())
        {
            return this.getName().compareTo(other.getName());
        }
        else
        {
            return  diem.compareTo(diem1);
        }


    }
}
class WriteAndReadFile
{
    private String sR;

    public String getsR() {
        return sR;
    }

    public void setsR(String sR) {
        this.sR = sR;
    }
    private  String nameofFile;

    public String getNameofFile() {
        return nameofFile;
    }

    public void setNameofFile(String nameofFile) {
        this.nameofFile = nameofFile;
    }

    public WriteAndReadFile()
    {
        this.setsR("");
    }
    public String ReadFile(String name) throws IOException
    {
        String file = name;
        FileInputStream fi=new FileInputStream(file);
        InputStreamReader isr =new InputStreamReader(fi,"UTF-8");
        BufferedReader br=new BufferedReader(isr);
        setsR("");String sNewLine;
        while((sNewLine=br.readLine())!=null)
        {
            //setsR(getsR()+sNewLine+"\r\n");
            sR=sR+sNewLine+"\r\n";
        }fi.close();
        return getsR();

    }
    public void WriteFile(String name) throws IOException
    {
        FileOutputStream fo=new FileOutputStream(name);
        OutputStreamWriter osw =new OutputStreamWriter(fo,"Utf-8");
        osw.write(sR.toString());
        osw.flush();fo.close();

    }
}




