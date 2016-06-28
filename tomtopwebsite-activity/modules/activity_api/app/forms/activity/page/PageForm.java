package forms.activity.page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import valueobject.activity.page.PageTitle;

public class PageForm implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<PageTitle> langs;
	
    private Integer iid;

    @NotNull
    private String curl;

    @NotNull
    private Integer itype;

    private String cbannerurl;

    @NotNull
    private Integer iwebsiteid;

    private String crecommendvalues;

    private String ccreateuser;

    private Date dcreatedate;

    private String cupdateuser;

    private Date dupdatedate;

    @NotNull
    private Integer ienable;

    @NotNull
    private Date denablestartdate;

    @NotNull
    private Date denableenddate;
    
    private Integer itemplateid;

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl == null ? null : curl.trim();
    }

    public Integer getItype() {
        return itype;
    }

    public void setItype(Integer itype) {
        this.itype = itype;
    }

    public String getCbannerurl() {
        return cbannerurl;
    }

    public void setCbannerurl(String cbannerurl) {
        this.cbannerurl = cbannerurl == null ? null : cbannerurl.trim();
    }

    public Integer getIwebsiteid() {
        return iwebsiteid;
    }

    public void setIwebsiteid(Integer iwebsiteid) {
        this.iwebsiteid = iwebsiteid;
    }

    public String getCrecommendvalues() {
        return crecommendvalues;
    }

    public void setCrecommendvalues(String crecommendvalues) {
        this.crecommendvalues = crecommendvalues == null ? null : crecommendvalues.trim();
    }

    public String getCcreateuser() {
        return ccreateuser;
    }

    public void setCcreateuser(String ccreateuser) {
        this.ccreateuser = ccreateuser == null ? null : ccreateuser.trim();
    }

    public Date getDcreatedate() {
        return dcreatedate;
    }

    public void setDcreatedate(Date dcreatedate) {
        this.dcreatedate = dcreatedate;
    }

    public String getCupdateuser() {
        return cupdateuser;
    }

    public void setCupdateuser(String cupdateuser) {
        this.cupdateuser = cupdateuser == null ? null : cupdateuser.trim();
    }

    public Date getDupdatedate() {
        return dupdatedate;
    }

    public void setDupdatedate(Date dupdatedate) {
        this.dupdatedate = dupdatedate;
    }

    public Integer getIenable() {
        return ienable;
    }

    public void setIenable(Integer ienable) {
        this.ienable = ienable;
    }

    public Date getDenablestartdate() {
        return denablestartdate;
    }

    public void setDenablestartdate(Date denablestartdate) {
        this.denablestartdate = denablestartdate;
    }

    public Date getDenableenddate() {
        return denableenddate;
    }

    public void setDenableenddate(Date denableenddate) {
        this.denableenddate = denableenddate;
    }

	public List<PageTitle> getLangs() {
		return langs;
	}

	public void setLangs(List<PageTitle> langs) {
		this.langs = langs;
	}

	public Integer getItemplateid() {
		return itemplateid;
	}

	public void setItemplateid(Integer itemplateid) {
		this.itemplateid = itemplateid;
	}
	
	
}