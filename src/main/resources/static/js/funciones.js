function eliminarAutor(id) {
  swal({
      title: "Seguro desea eliminar el registro",
      text: "Una vez borrado no podrá recuperar el registro.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((OK) => {
      if (OK) {
        $.ajax({
          url: "/autor/delete/" + id,
          success: function (res) {
            console.log(res);
          }
        })
        swal("Completado!! Su registro ha sido eliminado", {
          icon: "success",
        }).then((OK) => {
          if (OK) {
            location.href = "/autor/"
          }
        });
      } else {
        swal("Su registro no fue eliminado");
      }
    });
  }

function eliminarEditorial(id) {
  swal({
      title: "Seguro desea eliminar el registro",
      text: "Una vez borrado no podrá recuperar el registro.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((OK) => {
      if (OK) {
        $.ajax({
          url: "/editorial/delete/" + id,
          success: function (res) {
            console.log(res);
          }
        })
        swal("Completado!! Su registro ha sido eliminado", {
          icon: "success",
        })
        .then((OK) => {
          if (OK) {
            location.href = "/editorial/"
          }
        });
      } else {
        swal("Su registro no fue eliminado");
      }
    });
}

function eliminarLibro(id) {
  swal({
      title: "Seguro desea eliminar el registro",
      text: "Una vez borrado no podrá recuperar el registro.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((OK) => {
      if (OK) {
        $.ajax({
          url: "/libro/delete/" + id,
          success: function (res) {
            console.log(res);
          }
        })
        swal("Completado!! Su registro ha sido eliminado", {
          icon: "success",
        })
        .then((OK) => {
          if (OK) {
            location.href = "/libro/"
          }
        });
      } else {
        swal("Su registro no fue eliminado");
      }
    });
}

function goBack() {
  window.history.back();
}

var alertList = document.querySelectorAll('.alert')
alertList.forEach(function (alert) {
  new bootstrap.Alert(alert)
})