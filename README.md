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

   ![Carregamento](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/010fb95b-680d-4d2d-be5f-95a5675935a0)
   ![Login_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/9b065b71-bd16-436a-92b6-42758803b307)
   ![Login_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/238d4def-d44c-4290-ae9c-a8e3a9738490)
   ![CreateAccount_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/affc14b5-3752-4be2-b119-e5388dc7dcdd)
   ![CreateAccountDarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/2edb565c-6667-42f3-bf6e-c50ae8bf7849)
   ![Home_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/68575b23-8513-4829-99bd-b8bf3fb20bb0)
   ![Home_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/2976c6ce-2f2d-43e5-ad82-e5817db931f3)
   ![NewNotePad_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/d0421db7-4e55-47ba-97f7-06aa6a14ed3b)
   ![NewNotePad_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/65f0cdeb-78ab-4607-8d5f-dd7db7a9f97d)
   ![EditDeleteNotepad_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/3e3d8e8f-9c2f-48b5-85cf-32e6a226b48b)
   ![EditDeleteNotepad_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/383accb5-335e-4df1-978d-ae6227e1db31)
   ![Tema_LightMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/4f9b203f-a08d-4ee4-9efd-cee5b1029264)
   ![Tema_DarkMode](https://github.com/sennafilipe42/NotePad_DEV/assets/54420330/71e954a8-9a6f-4e65-a431-a90a2de23ebd)
   


