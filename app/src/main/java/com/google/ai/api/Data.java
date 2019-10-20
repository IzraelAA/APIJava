package com.google.ai.api;

public class Data {

    private String           Id;
    private String           Tgl;
    private String           Sandi;
    private String           debet;
    private String           kredit;
    private String           saldo;
    private String           pengesahan;



      public Boolean getTerbesar() {
        return Terbesar;
    }

    public void setTerbesar(Boolean terbesar) {
        Terbesar = terbesar;
    }

    private Boolean Terbesar;
    public Data (){

    }
    public String getDebet() {
        return debet;
    }

    public void setDebet(String debet) {
        this.debet = debet;
    }

    public String getKredit() {
        return kredit;
    }

    public void setKredit(String kredit) {
        this.kredit = kredit;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getPengesahan() {
        return pengesahan;
    }

    public void setPengesahan(String pengesahan) {
        this.pengesahan = pengesahan;
    }





    public Data (String Id,String Tgl,String Sandi,String debet,String kredit,String saldo,String pengesahan){
        this.Id = Id;
        this.Tgl = Tgl;
        this.Sandi = Sandi;
        this.debet =debet;
        this.kredit = kredit;
        this.saldo = saldo;
        this.pengesahan = pengesahan;

    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getTgl() {
        return Tgl;
    }
    public String setTgl(String tgl) {
        this.Tgl = tgl;
        return Tgl;
    }

    public String getSandi() {
        return Sandi;
    }

    public void setSandi(String sandi) {
        this.Sandi = sandi;
    }


}
