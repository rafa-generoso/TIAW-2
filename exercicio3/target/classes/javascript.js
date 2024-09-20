document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const nome = document.getElementById('nome').value;
    const idade = document.getElementById('idade').value;
    const sexo = document.getElementById('sexo').value;
    const especie = document.getElementById('especie').value;

    fetch('/personagem', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            nome: nome,
            idade: idade,
            sexo: sexo,
            especie: especie,
        })
    })
    .then(response => response.json())
    .then(data => {
        alert('Personagem adicionado com ID: ' + data);
        document.getElementById('addForm').reset();
    });
});

document.getElementById('searchButton').addEventListener('click', function() {
    const id = document.getElementById('searchId').value;

    fetch(`/personagem/${id}`)
    .then(response => response.text())
    .then(data => {
        document.getElementById('personagemInfo').innerText = data;
    });
});

document.getElementById('updateForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const id = document.getElementById('updateId').value;
    const nome = document.getElementById('updateNome').value;
    const idade = document.getElementById('updateIdade').value;
    const sexo = document.getElementById('updateSexo').value;
    const especie = document.getElementById('updateEspecie').value;

    fetch(`/personagem/uptade/${id}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            nome: nome,
            idade: idade,
            sexo: sexo,
            especie: especie,
        })
    })
    .then(response => response.text())
    .then(data => {
        alert('Personagem atualizado com ID: ' + id);
        document.getElementById('updateForm').reset();
    });
});

document.getElementById('deleteButton').addEventListener('click', function() {
    const id = document.getElementById('deleteId').value;

    fetch(`/personagem/remove/${id}`, {
        method: 'GET',
    })
    .then(response => {
        if (response.ok) {
            alert('Personagem excluído com ID: ' + id);
        } else {
            alert('Personagem não encontrado');
        }
    });
});

document.getElementById('showAllButton').addEventListener('click', function() {
    fetch('/personagem')
    .then(response => response.text())
    .then(data => {
        document.getElementById('allPersonagens').innerText = data;
    });
});
