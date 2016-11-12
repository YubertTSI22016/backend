package yuber.shares;

import java.util.Date;

public class DataVerticalReport {
	private Date dia;
	private Float saldo;

	public DataVerticalReport(Date dia, Float saldo) {
		this.dia = dia;
		this.saldo = saldo;
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

}
