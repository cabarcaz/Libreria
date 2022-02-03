package guiamvc.ejercicio1.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  @NotEmpty
  @Column(name = "NOMBRE")
  private String nombre;
  @Column(name = "DIPONIBLE")
  private Boolean alta;


  public String getId() {
    return id;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public Boolean getAlta() {
    return alta;
  }
  public void setAlta(Boolean alta) {
    this.alta = alta;
  }
}
