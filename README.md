# NotePad_DEV
Nesse repositório se encontra o projeto feito para cadeira de COMPUTACAO PARA DISPOSITIVOS MOVEIS, onde foi criado um APP Bloco de Notas chamado NotePad_DEV.
--
Grupo: Filipe Senna
--
Universidade: UniRitter
--
Cadeira: Computação para Dispositivos Móveis
--

Implementações necessárias para entrega do Projeto:

- ✔️Um login com validação remota (pode ser por mock com json-server);

- ✔️Fazer consumo de uma API REST externa (pode ser por mock com json-server);

- ✔️Possuir navegação entre Activities;

- ✔️Deve usar pelo menos 1 recylerView;

- ✔️Deve possui pelo menos 4 activities/interfaces

- ✔️Pode usar persistência em SQLite(usado FireBase Store);

- ✔️Deve usar SharedPreferences;
- 
Implementações criadas para o projeto:

🔥 Login com API FirebaseAuth:

    Durante o meu aprendizado, explorei o uso da API FirebaseAuth para implementar um sistema de login em um projeto. 
    Utilizei diferentes métodos disponíveis nessa API para realizar o login de usuários de maneiras distintas.
    O primeiro método que utilizei foi o "signInWithEmailAndPassword". Esse método permitiu que os usuários fizessem login 
    utilizando um e-mail e senha previamente cadastrados no Firebase. Com essa funcionalidade, os usuários puderam acessar 
    suas contas de forma segura e prática.

    Além disso, explorei o método "signInWithCredential", que possibilitou o login utilizando a conta do Google. 
    Ao utilizar essa função, os usuários puderam fazer login na plataforma utilizando suas credenciais do Google, 
    o que tornou o processo mais conveniente para aqueles que já possuíam uma conta no serviço.

    Por fim, também aprendi a utilizar o método "createUserWithEmailAndPassword". Com essa função, foi possível criar uma
    nova conta no Firebase, permitindo que os usuários se registrassem no sistema utilizando um e-mail e senha de sua escolha. 
    Esse método foi útil para ampliar a base de usuários do projeto e permitir que novas pessoas se envolvessem com a plataforma.

    Em resumo, por meio da API FirebaseAuth, aprendi a implementar um sistema de login em um projeto, utilizando métodos como "signInWithEmailAndPassword",
    "signInWithCredential" e "createUserWithEmailAndPassword". Essas funcionalidades foram fundamentais para permitir que os usuários fizessem login com 
    e-mail e senha cadastrados no Firebase, com a conta do Google ou para criar uma nova conta no sistema.


📱 Acitivities/interfaces, SharedPreferences e Firestore DataBase:

    Durante o meu aprendizado, trabalhei no desenvolvimento de um aplicativo que possui várias telas e funcionalidades. 
    Abaixo estão as principais tarefas que realizei e o que aprendi com cada uma delas:

    Carregamento_Activity: Implementei uma tela de carregamento para exibir ao iniciar o aplicativo. 
    Aprendi a criar uma experiência mais agradável para o usuário, fornecendo uma transição suave entre o momento em que 
    o aplicativo é aberto e quando está pronto para uso.

    CreateAccount_Activity: Desenvolvi uma tela que permite aos usuários criar uma conta usando o Firebase. 
    Com essa funcionalidade, aprendi a utilizar recursos de autenticação do Firebase para permitir que os usuários se 
    registrem no aplicativo de forma segura e confiável.

    Home_Activity: Criei a tela principal do aplicativo, onde são exibidos os NotePADs usando RecyclerView. Aprendi a organizar e apresentar 
    informações relevantes para os usuários de maneira eficiente, proporcionando uma experiência de usuário intuitiva.

    Login_Activity: Implementei uma tela de login que permite aos usuários acessarem o aplicativo usando uma conta do Google
    ou uma conta Firebase criada anteriormente. Com essa funcionalidade, aprendi a utilizar recursos de autenticação do Firebase 
    para validar as credenciais dos usuários e garantir a segurança do acesso ao aplicativo.

    NewNotePad_Activity: Criei uma tela para a criação, edição e exclusão de notepads, utilizando o Firestore Database. 
    Aprendi a integrar um banco de dados em tempo real ao aplicativo, permitindo aos usuários gerenciar suas anotações de forma dinâmica.

    Tema_Activity: Desenvolvi uma tela para permitir que os usuários alterem entre os modos DarkMode e LightMode. 
    Aprendi a implementar a troca de temas no aplicativo usando SharePreferences, proporcionando aos usuários a opção de personalizar 
    a aparência do aplicativo de acordo com suas preferências.

    Em resumo, durante o desenvolvimento do aplicativo, aprendi a criar telas de carregamento, login, criação de conta, tela principal, 
    gerenciamento de notas e alteração de temas. Além disso, aprofundei meus conhecimentos no uso do Firebase para autenticação de usuários 
    e no uso do Firestore Database para armazenamento de dados em tempo real. Essas habilidades foram essenciais para criar uma experiência
    de usuário agradável e funcional no aplicativo.


🎉 Telas do projeto NotePad_DEV:

   
   ![Carregamento](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/037d2396-d7ef-4804-a4c4-f1d7b7078587)
   ![Login_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/d87787cd-7c0c-4b98-8cc2-7f6f4f1d1318)
   ![Login_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/23d752eb-76df-408c-be10-03fd5fcaa1f0)
   ![CreateAccount_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/ed457d5e-e11c-4c13-9a0d-990cb5caaad7)
   ![CreateAccountDarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/b829a822-136c-4b74-a547-2bd2bfd204a0)
   ![Home_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/4e46d8b7-f50d-4450-a8e8-1bf6bdb9cff8)
   ![Home_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/24925270-a482-46fc-8a13-c02a1a3e9012)
   ![NewNotePad_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/465c248b-a244-49eb-b88c-b2c9c6139a50)
   ![NewNotePad_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/7c490d78-c429-4a88-883a-e6743a88b006)
   ![EditDeleteNotepad_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/fe310b25-d234-499b-904d-650d77ce32ee)
   ![EditDeleteNotepad_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/dedb77e8-1eae-4ec7-8949-9833fbfd1003)
   ![Tema_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/80f1e142-51ad-498a-9316-97a75d11296f)
   ![Tema_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/03268f9a-1bb7-4494-8ad0-f82873e7d5a8)
 
   

