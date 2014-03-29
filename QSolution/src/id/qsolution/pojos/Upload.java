package id.qsolution.pojos;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Upload implements Serializable {
	
	
	List<Outlet> outlets = new ArrayList<Outlet>();
	List<KunjunganSurveyorOutletPosm> outletPosm = new ArrayList<KunjunganSurveyorOutletPosm>();
	List<KunjunganSurveyorPhoto> kunjunganPhoto = new ArrayList<KunjunganSurveyorPhoto>();
	List<KunjunganSurveyorRak> kunjunganRak = new ArrayList<KunjunganSurveyorRak>();
	List<KunjunganSurveyor> kunjunganSurveyor = new ArrayList<KunjunganSurveyor>();
	List<KunjunganSurveyorRakFoto> kunjunganRakFoto = new ArrayList<KunjunganSurveyorRakFoto>();
	List<KunjunganSurveyorRakPosm> kunjunganRakPosm = new ArrayList<KunjunganSurveyorRakPosm>();
	List<KunjunganSurveyorRakSku> kunjunganRakSku = new ArrayList<KunjunganSurveyorRakSku>();
	List<KunjunganSurveyorRakSkuPromosi> kunjunganRakSkuPromosi = new ArrayList<KunjunganSurveyorRakSkuPromosi>();
	List<OutletFasilitas> outletFasilitas = new ArrayList<OutletFasilitas>();
	
	public List<OutletFasilitas> getOutletFasilitas() {
		return outletFasilitas;
	}
	public void setOutletFasilitas(List<OutletFasilitas> outletFasilitas) {
		this.outletFasilitas = outletFasilitas;
	}
	public List<Outlet> getOutlets() {
		return outlets;
	}
	public void setOutlets(List<Outlet> outlets) {
		this.outlets = outlets;
	}
	public List<KunjunganSurveyorOutletPosm> getOutletPosm() {
		return outletPosm;
	}
	public void setOutletPosm(List<KunjunganSurveyorOutletPosm> outletPosm) {
		this.outletPosm = outletPosm;
	}
	public List<KunjunganSurveyorPhoto> getKunjunganPhoto() {
		return kunjunganPhoto;
	}
	public void setKunjunganPhoto(List<KunjunganSurveyorPhoto> kunjunganPhoto) {
		this.kunjunganPhoto = kunjunganPhoto;
	}
	public List<KunjunganSurveyorRak> getKunjunganRak() {
		return kunjunganRak;
	}
	public void setKunjunganRak(List<KunjunganSurveyorRak> kunjunganRak) {
		this.kunjunganRak = kunjunganRak;
	}
	public List<KunjunganSurveyor> getKunjunganSurveyor() {
		return kunjunganSurveyor;
	}
	public void setKunjunganSurveyor(List<KunjunganSurveyor> kunjunganSurveyor) {
		this.kunjunganSurveyor = kunjunganSurveyor;
	}
	public List<KunjunganSurveyorRakFoto> getKunjunganRakFoto() {
		return kunjunganRakFoto;
	}
	public void setKunjunganRakFoto(List<KunjunganSurveyorRakFoto> kunjunganRakFoto) {
		this.kunjunganRakFoto = kunjunganRakFoto;
	}
	public List<KunjunganSurveyorRakPosm> getKunjunganRakPosm() {
		return kunjunganRakPosm;
	}
	public void setKunjunganRakPosm(List<KunjunganSurveyorRakPosm> kunjunganRakPosm) {
		this.kunjunganRakPosm = kunjunganRakPosm;
	}
	public List<KunjunganSurveyorRakSku> getKunjunganRakSku() {
		return kunjunganRakSku;
	}
	public void setKunjunganRakSku(List<KunjunganSurveyorRakSku> kunjunganRakSku) {
		this.kunjunganRakSku = kunjunganRakSku;
	}
	public List<KunjunganSurveyorRakSkuPromosi> getKunjunganRakSkuPromosi() {
		return kunjunganRakSkuPromosi;
	}
	public void setKunjunganRakSkuPromosi(
			List<KunjunganSurveyorRakSkuPromosi> kunjunganRakSkuPromosi) {
		this.kunjunganRakSkuPromosi = kunjunganRakSkuPromosi;
	}
	
	
}
