package yuber.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import yuber.shares.DataVerticalReport;

/**
 * Entity implementation class for Entity: VerticalReport
 *
 */
@Entity
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "ReporteVertical",
	query = "select sum(precio) as saldo, date_trunc('day', fecha) as dia from servicio where fecha >= :start  and fecha <= (CURRENT_DATE + 1) group by dia",
        resultClass = VerticalReport.class
	)
})
public class VerticalReport implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	@Id
    private Date dia;
	private Float saldo;
 
	public VerticalReport() {
		super();
	}
	 
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public Float getSaldo() {
		return saldo;
	}
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	public DataVerticalReport toData(){
		return new DataVerticalReport(this.getDia(), this.getSaldo());
	}
}
