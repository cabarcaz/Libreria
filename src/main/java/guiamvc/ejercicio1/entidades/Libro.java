package guiamvc.ejercicio1.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "libro")
public class Libro {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;


  @Column(name = "ISBN")
  private Long isbn;
  
  @NotEmpty
  @Column(name = "TITULO")
  private String titulo;
  
  @Column(name = "PUBLICACION")
  private Integer anio;
   
  @Column(name="CANTIDAD")
  private Integer ejemplares;
  
  @Column(name = "PRESTADOS")
  private Integer ejemplaresPrestados;
  
  @Column(name = "RESTANTES")
  private Integer ejemplaresRestantes;
  
  @Column(name = "DISPONIBLE")
  private Boolean alta;

  private String imagen;


  @OneToOne
  @JoinColumn(name = "autor_id")
  private Autor autor;

  @OneToOne
  @JoinColumn(name = "editorial_id")
  private Editorial editorial;

  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = imagen;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getIsbn() {
    return isbn;
  }

  public void setIsbn(Long isbn) {
    this.isbn = isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Integer getAnio() {
    return anio;
  }

  public void setAnio(Integer anio) {
    this.anio = anio;
  }

  public Integer getEjemplares() {
    return ejemplares;
  }

  public void setEjemplares(Integer ejemplares) {
    this.ejemplares = ejemplares;
  }

  public Integer getEjemplaresPrestados() {
    return ejemplaresPrestados;
  }

  public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
    this.ejemplaresPrestados = ejemplaresPrestados;
  }

  public Integer getEjemplaresRestantes() {
    return ejemplaresRestantes;
  }

  public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
    this.ejemplaresRestantes = ejemplaresRestantes;
  }

  public Boolean getAlta() {
    return alta;
  }

  public void setAlta(Boolean alta) {
    this.alta = alta;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public Editorial getEditorial() {
    return editorial;
  }

  public void setEditorial(Editorial editorial) {
    this.editorial = editorial;
  }

}
