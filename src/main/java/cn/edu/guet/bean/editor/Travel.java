package cn.edu.guet.bean.editor;

public class Travel {

  private String travelid;
  private String travelurl;
  private String travelalt;
  private String travelhref;

    public String getTravelid() {
        return travelid;
    }

    public void setTravelid(String travelid) {
        this.travelid = travelid;
    }

    public String getTravelurl() {
        return travelurl;
    }

    public void setTravelurl(String travelurl) {
        this.travelurl = travelurl;
    }

    public String getTravelalt() {
        return travelalt;
    }

    public void setTravelalt(String travelalt) {
        this.travelalt = travelalt;
    }

    public String getTravelhref() {
        return travelhref;
    }

    public void setTravelhref(String travelhref) {
        this.travelhref = travelhref;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "travelid='" + travelid + '\'' +
                ", travelurl='" + travelurl + '\'' +
                ", travelalt='" + travelalt + '\'' +
                ", travelhref='" + travelhref + '\'' +
                '}';
    }
}
