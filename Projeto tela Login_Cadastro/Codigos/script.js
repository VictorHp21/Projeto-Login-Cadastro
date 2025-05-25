// Script para abrir apenas o formulário clicado 

    function mostrarFormulario(id) {
        document.querySelectorAll('.form1-wrapper').forEach(form => {
            if (form.id === id) {
                form.classList.toggle('active');
            } else {
                form.classList.remove('active');
            }
        });
    }






  // Array  funcionários
  let funcionarios = [];

  // CADASTRAR
  function cadastrarFuncionario() {
    const nome = document.getElementById("nome").value;
    const idade = document.getElementById("idade").value;
    const setor = document.getElementById("departamento").value;
  
    if (nome && idade && setor) {
      funcionarios.push({ nome, idade, setor });
  
      alert("Funcionário cadastrado com sucesso!");
      document.getElementById("nome").value = "";
      document.getElementById("idade").value = "";
      document.getElementById("departamento").value = "";
    } else {
      alert("Por favor, preencha todos os campos.");
    }
  }
  

  // LISTAR
  function listarFuncionarios() {
    const lista = document.getElementById("form-lista");
    lista.innerHTML = "<h3>Funcionários Cadastrados:</h3>";
  
    if (funcionarios.length === 0) {
      lista.innerHTML += "<p>Nenhum funcionário cadastrado.</p>";
    } else {
      funcionarios.forEach((f, i) => {
        lista.innerHTML += `
          <div>
            <strong>${f.nome}</strong> - Idade: ${f.idade} - Setor: ${f.setor}
           
          </div>
        `;
      });
    }
  }

  // DELETAR
  function deletarFuncionarioPorNome() {
    const nomeParaDeletar = document.getElementById("nome-deletar").value.trim();
    const mensagem = document.getElementById("mensagem-deletar");
  
    if (nomeParaDeletar === "") {
      mensagem.textContent = "Por favor, digite um nome.";
      return;
    }
  
   
    const index = funcionarios.findIndex(f => f.nome.toLowerCase() === nomeParaDeletar.toLowerCase());
  
    if (index !== -1) {
      funcionarios.splice(index, 1); // Remove do array
      mensagem.textContent = `Funcionário "${nomeParaDeletar}" deletado com sucesso.`;
      listarFuncionarios(); // Atualiza a lista, se estiver visível
    } else {
      mensagem.textContent = `Funcionário "${nomeParaDeletar}" não encontrado.`;
    }
  
    document.getElementById("nome-deletar").value = "";
  }
  

  // MOSTRAR FORMULÁRIO PARA ATUALIZAR
  function atualizarFuncionarioPorNome() {
    const nome = document.getElementById("nome-atualizar").value.trim();
    const novaIdade = document.getElementById("nova-idade").value.trim();
    const novoSetor = document.getElementById("novo-setor").value;
    const mensagem = document.getElementById("mensagem-atualizar");
  
    if (!nome || !novaIdade || !novoSetor) {
      mensagem.textContent = "Preencha todos os campos.";
      return;
    }
  
    const funcionario = funcionarios.find(f => f.nome.toLowerCase() === nome.toLowerCase());
  
    if (funcionario) {
      funcionario.idade = novaIdade;
      funcionario.setor = novoSetor;
      mensagem.textContent = `Funcionário "${nome}" atualizado com sucesso.`;
      listarFuncionarios();
    } else {
      mensagem.textContent = `Funcionário "${nome}" não encontrado.`;
    }
  
    // Limpa os campos
    document.getElementById("nome-atualizar").value = "";
    document.getElementById("nova-idade").value = "";
    document.getElementById("novo-setor").value = "";
  }
  

  // ATUALIZAR
  function atualizarFuncionario(index) {
    const novaIdade = document.getElementById("novaIdade").value;
    const novoSetor = document.getElementById("novoSetor").value;

    funcionarios[index].idade = novaIdade;
    funcionarios[index].setor = novoSetor;

    alert("Funcionário atualizado com sucesso!");
    listarFuncionarios();
  }

  // CAPTURA DO FORMULÁRIO DE CADASTRO
  document.querySelector("#form-cadastrar form").addEventListener("submit", function (e) {
    e.preventDefault();
    const nome = document.getElementById("nome").value;
    const idade = document.getElementById("idade").value;
    const setor = document.getElementById("departamento").value;

    if (!nome || !idade || !setor) {
      alert("Preencha todos os campos!");
      return;
    }

    cadastrarFuncionario(nome, idade, setor);
    this.reset(); // Limpa o formulário
  });

