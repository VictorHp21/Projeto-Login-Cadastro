const card = document.querySelector(".card"),
  navButtons = document.querySelectorAll(".card-nav button");
const selectView = (view) => {
  for (let button of navButtons) {
    button.classList.remove("active");

    if (button.classList.contains(view)) {
      button.classList.add("active");
    }
  }

  if (view === "signin") {
    card.style.setProperty("--forms", "0");
    card.style.setProperty("--hero", "0");
    card.style.setProperty("--active", "33.33%");
  } else {
    card.style.setProperty("--forms", "-100%");
    card.style.setProperty("--hero", "-100%");
    card.style.setProperty("--active", "66.66%");
  }
};

let resizeTimer;

window.addEventListener("resize", () => {
  card.classList.add("resizing");

  clearTimeout(resizeTimer);
  resizeTimer = setTimeout(() => card.classList.remove("resizing"), 150);
});


// login

document.getElementById("signin").addEventListener("submit", async function (e) {

  e.preventDefault()

  const email = document.getElementById("login-mail").value
  const senha = document.getElementById("login-senha").value

  const usuario = {
    email: email,
    senha: senha
  };

  try {

    const respostaLogin = await fetch("http://localhost:8080/user/login", {
      method: "POST",

      headers: {
        "Content-Type": "application/json"
      },

      body: JSON.stringify(usuario)

    });

    const resultado = await respostaLogin.text();



    if (respostaLogin.ok) {

      const usuarioLogado = JSON.parse(resultado);

      localStorage.setItem(
        "usuario",
        JSON.stringify(usuarioLogado)
      );

      document.getElementById("signin").reset();

      window.location.href = "Pagina_Cadastro.html";

    } else {

      alert(resultado);

    }

  } catch (erro) {
    console.error("Erro na requisição", erro);

    alert("Não foi possível conectar ao servidor")
  }



})