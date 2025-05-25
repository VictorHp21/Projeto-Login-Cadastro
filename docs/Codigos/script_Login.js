function login(event) {
    event.preventDefault();
    
    const ilogin = document.getElementById("ilogin").value;
    const senha = document.getElementById("isenha").value;
   

    if (ilogin === "admin" && senha === "1234") {
      window.location.href = "Pagina_Cadastro.html";
    } else {
      alert("Login ou senha incorretos!");
    }
  }

  document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");
    form.addEventListener("submit", login);
});