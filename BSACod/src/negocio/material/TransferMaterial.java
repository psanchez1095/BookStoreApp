package negocio.material;

public class TransferMaterial {

	private Integer id;
	private String tipo;
	private Boolean activo;


	public Integer getId() {
		return this.id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}