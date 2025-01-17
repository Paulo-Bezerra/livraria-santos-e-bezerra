package com.imd.livrariasantosebezerra.banco

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.imd.livrariasantosebezerra.modelo.Livro
import com.imd.livrariasantosebezerra.modelo.Usuario

class Banco(context: Context) : SQLiteOpenHelper(context, BD_NOME, BD_FACTORY, BD_VERSAO) {
  companion object {
    private const val BD_NOME = "livraria.db"
    private val BD_FACTORY = null
    private const val BD_VERSAO = 1

    // Definição da tabela usuários
    private const val TABELA_USUARIOS = "usuarios"
    private const val NOME_USUARIO = "nome"
    private const val USUARIO_USUARIO = "usuario"
    private const val SENHA_USUARIO = "senha"


    private const val CRIAR_TABELA_USUARIOS =
      """
      CREATE TABLE IF NOT EXISTS $TABELA_USUARIOS (
        $NOME_USUARIO TEXT NOT NULL CHECK (LENGTH($NOME_USUARIO) > 0),
        $USUARIO_USUARIO TEXT PRIMARY KEY CHECK (LENGTH($USUARIO_USUARIO) > 0),
        $SENHA_USUARIO TEXT NOT NULL CHECK (LENGTH($SENHA_USUARIO) > 0)        
      )
      """

    // Campos da tabela de livros
    private const val TABELA_LIVROS = "livros"
    private const val TITULO_LIVRO = "titulo"
    private const val AUTOR_LIVRO = "autor"
    private const val EDITORA_LIVRO = "editora"
    private const val ANO_LIVRO = "ano"
    private const val ISBN_LIVRO = "isbn"
    private const val DESCRICAO_LIVRO = "descricao"
    private const val URL_LIVRO = "url"

    private const val CRIAR_TABELA_LIVROS =
      """
      CREATE TABLE IF NOT EXISTS $TABELA_LIVROS (
        $TITULO_LIVRO TEXT NOT NULL CHECK (LENGTH($TITULO_LIVRO) > 0),
        $AUTOR_LIVRO TEXT NOT NULL DEFAULT '',
        $EDITORA_LIVRO TEXT NOT NULL DEFAULT '',
        $ANO_LIVRO TEXT NOT NULL DEFAULT '',
        $ISBN_LIVRO TEXT PRIMARY KEY CHECK (LENGTH($ISBN_LIVRO) == 10 OR LENGTH($ISBN_LIVRO) == 13),
        $DESCRICAO_LIVRO TEXT NOT NULL DEFAULT '',
        $URL_LIVRO TEXT NOT NULL DEFAULT ''
      )
      """

    private const val INSERIR_ADMINISTRADOR =
      """
      INSERT INTO 
      $TABELA_USUARIOS 
      ($NOME_USUARIO, $USUARIO_USUARIO, $SENHA_USUARIO)
      VALUES 
      ('Administrador', 'admin', 'admin')
      """

    private const val EXEMPLOS_LIVROS =
      """
      INSERT INTO
      $TABELA_LIVROS 
      ($TITULO_LIVRO, $AUTOR_LIVRO, $EDITORA_LIVRO, $ANO_LIVRO, $ISBN_LIVRO, $DESCRICAO_LIVRO, $URL_LIVRO)
      VALUES
      ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'HarperCollins', '1943', '8595081514', 'Uma fábula sobre amizade e vida.', 'https://upload.wikimedia.org/wikipedia/pt/4/47/O-pequeno-pr%C3%ADncipe.jpg'),
      ('Dom Casmurro', 'Machado de Assis', 'Record', '1899', '8501054119', 'Um romance psicológico sobre ciúmes e traição.', 'https://upload.wikimedia.org/wikipedia/commons/0/05/DomCasmurroMachadodeAssis.jpg'),
      ('A Metamorfose', 'Franz Kafka', 'Companhia das Letras', '1915', '8571646856', 'Uma alegoria sobre a alienação e a burocracia.', 'https://upload.wikimedia.org/wikipedia/commons/a/a5/Franz_Kafka_Die_Verwandlung_1916_Orig.-Pappband.jpg'),
      ('O Alquimista', 'Paulo Coelho', 'Sextante', '1988', '857542758X', 'Uma história sobre a busca por um sonho.', 'https://upload.wikimedia.org/wikipedia/pt/5/51/O_Alquimista.jpg'),
      ('Um Estudo em Vermelho', 'Arthur Conan Doyle', 'L&PM', '1887', '8525408115', 'O primeiro romance de Sherlock Holmes.', 'https://upload.wikimedia.org/wikipedia/commons/2/2c/ArthurConanDoyle_AStudyInScarlet_annual.jpg'),
      ('Um conto de duas cidades', 'Charles Dickens', 'Princips', '1859', '6550970385', 'Um conto de duas cidades é aventura, romance e tragédia a serviço da crítica social.', 'https://upload.wikimedia.org/wikipedia/commons/f/f1/A_Tale_of_Two_Cities_title_page.png'),
      ('Dom Quixote', 'Miguel de Cervantes Saavedra', 'FTD', '1605', '8532287441', 'Depois de ler muitas novelas de cavalaria, um fidalgo pobre resolve se tornar cavaleiroandante. Adota o nome de Dom Quixote de la Mancha, em homenagem à região da Espanha em que nasceu.','https://covers.openlibrary.org/b/id/10630455-M.jpg'),
      ('Hamlet', 'William Shakespeare', 'Penguin Classics', '1603', '0141396504', 'Uma tragédia clássica sobre vingança e moralidade.', 'https://covers.openlibrary.org/b/id/13834500-M.jpg'),
      ('Ilíada', 'Homero', 'Penguin Classics', '800 a.C.', '0140445927', 'Os leitores confiam que a série fornecerá textos confiáveis, enriquecidos por introduções e notas de acadêmicos renomados e autores contemporâneos, bem como traduções atualizadas por tradutores premiados.', 'https://covers.openlibrary.org/b/id/6517876-M.jpg'),
      ('Romeu e Julieta', 'William Shakespeare', 'Penguin Classics', '1597', '0141396474', 'Romeu e Julieta é uma tragédia escrita por William Shakespeare no início de sua carreira sobre dois jovens amantes infelizes cujas mortes acabam reconciliando suas famílias em conflito.', 'https://covers.openlibrary.org/b/id/9151223-M.jpg'),
      ('1984', 'George Orwell', 'Companhia das Letras', '1949', '8535914846', 'Publicada originalmente em 1949, a distopia futurista 1984 é um dos romances mais influentes do século XX, um inquestionável clássico moderno. Lançada poucos meses antes da morte do autor, é uma obra magistral que ainda se impõe como uma poderosa reflexão ficcional sobre a essência nefasta de qualquer forma de poder totalitário. Winston, herói de 1984, último romance de George Orwell, vive aprisionado na engrenagem totalitária de uma sociedade completamente dominada pelo Estado, onde tudo é feito coletivamente, mas cada qual vive sozinho. Ninguém escapa à vigilância do Grande Irmão, a mais famosa personificação literária de um poder cínico e cruel ao infinito, além de vazio de sentido histórico. De fato, a ideologia do Partido dominante em Oceania não visa nada de coisa alguma para ninguém, no presente ou no futuro.', 'https://covers.openlibrary.org/b/id/8172473-M.jpg'),
      ('Orgulho e Preconceito', 'Jane Austen', 'Penguin Classics', '1813', '0141439513', 'O romance mais popular de Austen, a história inesquecível de Elizabeth Bennet e o Sr. Darcy Poucos deixaram de se encantar com o espírito espirituoso e independente de Elizabeth Bennet no adorado clássico de Austen, Orgulho e Preconceito. Quando Elizabeth Bennet conhece pela primeira vez o solteiro cobiçado Fitzwilliam Darcy, ela o acha arrogante e convencido; ele é indiferente à sua boa aparência e mente viva. Quando ela descobre mais tarde que Darcy se envolveu no relacionamento problemático entre seu amigo Bingley e sua amada irmã Jane, ela está determinada a não gostar dele mais do que nunca. Na brilhante comédia de costumes que se segue, Jane Austen nos mostra a loucura de julgar pelas primeiras impressões e evoca soberbamente as amizades, fofocas e esnobes da vida provinciana da classe média. Esta edição da Penguin Classics, baseada na primeira edição de Austen, contém a introdução original da Penguin Classics por Tony Tanner e uma introdução e notas atualizadas por Viven Jones. Por mais de setenta anos, a Penguin tem sido a principal editora de literatura clássica no mundo de língua inglesa. Com mais de 1.700 títulos, a Penguin Classics representa uma estante global das melhores obras ao longo da história e em todos os gêneros e disciplinas. Os leitores confiam na série para fornecer textos confiáveis aprimorados por introduções e notas de acadêmicos renomados e autores contemporâneos, bem como traduções atualizadas por tradutores premiados.', 'https://covers.openlibrary.org/b/id/12645114-M.jpg'),
      ('Os Miseráveis', 'Victor Hugo', 'Martin Claret', '1862', '8544000002', 'Um clássico da literatura mundial, esta obra é uma poderosa denúncia a todos os tipos de injustiça humana. Narra a emocionante história de Jean Valjean — o homem que, por ter roubado um pão, é condenado a dezenove anos de prisão. Os miseráveis é um livro inquietantemente religioso e político, com uma das narrativas mais envolventes já criadas.', 'https://covers.openlibrary.org/b/id/12919122-M.jpg'),
      ('O Senhor dos Anéis', 'J. R. R. Tolkien', 'HarperCollins', '1954', '9786555114249', 'Nos tempos antigos da Terra-média, mais especificamente na Segunda Era, os ferreiros-élficos de Eregion, liderados por Celebrimbor, criaram os Anéis de Poder. Mas o que eles não sabiam, era que em Mordor, Sauron, o Senhor das Trevas, forjou o Um Anel, enchendo-o com seu próprio poder para que pudesse governar todos os outros. Depois da Batalha da Última Aliança, onde Elfos (liderados por Elrond e Gil-galad) e os poderosos Homens de Númenor (liderados por Elendil e Isildur) lutaram juntos contra Sauron, ele foi derrotado e o seu Anel foi tirado dele. Sauron vagou em forma de um espírito derrotado por séculos, até que depois de ter reunido um pouco de forças, buscou seu artefato em vão. Ele estava perdido e se tornara apenas uma lenda, conhecida por poucos, na Terra-média. Depois de muitas eras, o Um Anel caiu por acaso nas mãos do improvável hobbit Bilbo Bolseiro do Condado. Quando Bilbo atingiu seu onzentésimo primeiro aniversário, acabou legando a seu jovem sobrinho Frodo o Anel Governante. Porém, o mago Gandalf, depois de muito estudar, descobre a verdade sobre o artefato e indica a Frodo uma missão perigosa: viajar pela Terra-média, mergulhar nas sombras de Mordor e destruir o Anel, lançando-o na Fenda da Perdição. O Senhor dos Anéis é a grande obra-prima de J.R.R. Tolkien. Considerado o primeiro grande épico de fantasia moderno, conquistou milhões de leitores e tornou-se padrão de referência para todas as outras obras do gênero até hoje. Apesar de ter sido publicado em três volumes – A Sociedade do Anel, As Duas Torres e O Retorno do Rei –, O Senhor dos Anéis não é exatamente uma trilogia, mas um único grande romance que só pode ser compreendido em seu conjunto, segundo a concepção de seu autor.', 'https://harpercollins.com.br/cdn/shop/products/9786555114249.jpg'),
      ('Duna', 'Frank Herbert', 'Aleph', '1965', '857657313X', 'Uma estonteante mistura de aventura e misticismo, ecologia e política, este romance ganhador dos prêmios Hugo e Nebula deu início a uma das mais épicas histórias de toda a ficção científica. Duna é um triunfo da imaginação, que influenciará a literatura para sempre.', 'https://editoraaleph.com.br/cdn/shop/files/alp_testecapas_700x1000_Duna.png'),
      ('O Código Da Vinci', 'Dan Brown', 'Arqueiro', '2003', '9788575421130', 'História de suspense com informações sobre obras de arte, documentos e rituais secretos, entremeada de pesquisas notáveis. Um thriller que explora segredos ocultos na obra de Leonardo da Vinci.', 'https://covers.openlibrary.org/b/id/10316848-M.jpg'),
      ('As Crônicas de Nárnia', 'C. S. Lewis', 'WMF Martins Fontes', '1950', '857827069X', 'Sete histórias de fantasia no mundo mágico de Nárnia.', 'https://covers.openlibrary.org/b/id/8612354-M.jpg'),
      ('O Hobbit', 'J. R. R. Tolkien', 'HarperCollins', '1937', '9788595084742', 'Edição com mapas, capa dura, fitilho, as ilustrações originais de J.R.R. Tolkien e um pôster de Valfenda exclusivo! Bilbo Bolseiro era um dos mais respeitáveis hobbits de todo o Condado até que, um dia, o mago Gandalf bate à sua porta. A partir de então, toda sua vida pacata e campestre soprando anéis de fumaça com seu belo cachimbo começa a mudar. Ele é convocado a participar de uma aventura por ninguém menos do que Thorin Escudo-de-Carvalho, um príncipe do poderoso povo dos Anãos. Esta jornada fará Bilbo, Gandalf e 13 anãos atravessarem a Terra-média, passando por inúmeros perigos, como os imensos trols, as Montanhas Nevoentas infestadas de gobelins ou a muito antiga e misteriosa Trevamata, até chegarem (se conseguirem) na Montanha Solitária. Lá está um incalculável tesouro, mas há um porém. Deitado em cima dele está Smaug, o Dourado, um dragão malicioso que... bem, você terá que ler para descobrir. Lançado em 1937, O Hobbit é um divisor de águas na literatura de fantasia mundial. Mais de 80 anos após a sua publicação, o livro que antecede os ocorridos em O Senhor dos Anéis continua arrebatando fãs de todas as idades, talvez pelo seu tom brincalhão com uma pitada de magia élfica, ou talvez porque J.R.R. Tolkien tenha escrito o melhor livro infantojuvenil de todos os tempos.', 'https://covers.openlibrary.org/b/id/14826646-M.jpg'),
      ('Sapiens: Uma breve historia da humanidade', 'Yuval Noah Harari', 'Companhia das Letras', '2011', '9788535933925', 'O planeta Terra tem cerca de 4,5 bilhões de anos. Numa fração ínfima desse tempo, uma espécie entre incontáveis outras o dominou: nós, humanos. Somos os animais mais evoluídos e mais destrutivos que jamais viveram. Sapiens é a obra-prima de Yuval Noah Harari e o consagrou como um dos pensadores mais brilhantes da atualidade. Num feito surpreendente, que já fez deste livro um clássico contemporâneo, o historiador israelense aplica uma fascinante narrativa histórica a todas as instâncias do percurso humano sobre a Terra. Da Idade da Pedra ao Vale do Silício, temos aqui uma visão ampla e crítica da jornada em que deixamos de ser meros símios para nos tornarmos os governantes do mundo. Harari se vale de uma abordagem multidisciplinar que preenche as lacunas entre história, biologia, filosofia e economia, e, com uma perspectiva macro e micro, analisa não apenas os grandes acontecimentos, mas também as mudanças mais sutis notadas pelos indivíduos.', 'https://covers.openlibrary.org/b/id/14540965-M.jpg'),
      ('Homo Deus: Uma breve história do amanhã', 'Yuval Noah Harari', 'Companhia das Letras', '2015', '9788535928198', 'Neste Homo Deus- uma breve história do amanhã, Yuval Noah Harari, autor do estrondoso best-seller Sapiens- uma breve história da humanidade, volta a combinar ciência, história e filosofia, desta vez para entender quem somos e descobrir para onde vamos. Sempre com um olhar no passado e nas nossas origens, Harari investiga o futuro da humanidade em busca de uma resposta tão difícil quanto essencial - depois de séculos de guerras, fome e pobreza, qual será nosso destino na Terra? A partir de uma visão absolutamente original de nossa história, ele combina pesquisas de ponta e os mais recentes avanços científicos à sua conhecida capacidade de observar o passado de uma maneira inteiramente nova. Assim, descobrir os próximos passos da evolução humana será também redescobrir quem fomos e quais caminhos tomamos para chegar até aqui.', 'https://covers.openlibrary.org/b/id/12370424-M.jpg')
      """
  }

  override fun onCreate(banco: SQLiteDatabase?) {
    banco?.execSQL(CRIAR_TABELA_USUARIOS)
    banco?.execSQL(CRIAR_TABELA_LIVROS)
    banco?.execSQL(INSERIR_ADMINISTRADOR)
    banco?.execSQL(EXEMPLOS_LIVROS)
  }

  override fun onUpgrade(banco: SQLiteDatabase?, versaoAntiga: Int, versaoNova: Int) {}

  fun cadastrarUsuario(usuario: Usuario): Boolean {
    val conteudo = ContentValues()
    conteudo.put(NOME_USUARIO, usuario.nome.trim())
    conteudo.put(USUARIO_USUARIO, usuario.usuario.trim())
    conteudo.put(SENHA_USUARIO, usuario.senha.trim())
    val nullColumnHack = null
    val banco = this.writableDatabase
    val resultado = banco.insert(TABELA_USUARIOS, nullColumnHack, conteudo)
    banco.close()
    return resultado >= 0L
  }

  fun autenticarUsuario(usuario: String = "", senha: String = ""): Boolean {
    val whereClause = "$USUARIO_USUARIO = ? AND $SENHA_USUARIO = ?"
    val whereArgs = arrayOf(usuario.trim(), senha.trim())
    val sql = "SELECT * FROM $TABELA_USUARIOS WHERE $whereClause"
    val banco = this.readableDatabase
    val cursor = banco.rawQuery(sql, whereArgs)
    val resultado = cursor.count > 0
    cursor.close()
    banco.close()
    return resultado
  }

  fun redefinirSenha(usuario: String = "", senha: String = ""): Boolean {
    val conteudo = ContentValues()
    conteudo.put(SENHA_USUARIO, senha.trim())
    val whereClause = "$USUARIO_USUARIO = ?"
    val whereArgs = arrayOf(usuario.trim())
    val banco = this.writableDatabase
    val resultado = banco.update(TABELA_USUARIOS, conteudo, whereClause, whereArgs)
    banco.close()
    return resultado > 0L
  }

  fun cadastrarLivro(livro: Livro): Boolean {
    val conteudo = ContentValues()
    conteudo.put(TITULO_LIVRO, livro.titulo.trim())
    conteudo.put(AUTOR_LIVRO, livro.autor.trim())
    conteudo.put(EDITORA_LIVRO, livro.editora.trim())
    conteudo.put(ANO_LIVRO, livro.ano.trim())
    conteudo.put(ISBN_LIVRO, livro.isbn.trim())
    conteudo.put(DESCRICAO_LIVRO, livro.descricao.trim())
    conteudo.put(URL_LIVRO, livro.url.trim())
    val nullColumnHack = null
    val banco = this.writableDatabase
    val resultado = banco.insert(TABELA_LIVROS, nullColumnHack, conteudo)
    banco.close()
    return resultado >= 0L
  }

  fun atualizarLivro(livro: Livro): Boolean {
    val conteudo = ContentValues()
    conteudo.put(TITULO_LIVRO, livro.titulo.trim())
    conteudo.put(AUTOR_LIVRO, livro.autor.trim())
    conteudo.put(EDITORA_LIVRO, livro.editora.trim())
    conteudo.put(ANO_LIVRO, livro.ano.trim())
    conteudo.put(DESCRICAO_LIVRO, livro.descricao.trim())
    conteudo.put(URL_LIVRO, livro.url.trim())
    val whereClause = "$ISBN_LIVRO = ?"
    val whereArgs = arrayOf(livro.isbn.trim())
    val banco = this.writableDatabase
    val resultado = banco.update(TABELA_LIVROS, conteudo, whereClause, whereArgs)
    banco.close()
    return resultado > 0L
  }

  fun deletarLivro(isbn: String = ""): Boolean {
    val whereClause = "$ISBN_LIVRO = ?"
    val whereArgs = arrayOf(isbn.trim())
    val banco = this.writableDatabase
    val resultado = banco.delete(TABELA_LIVROS, whereClause, whereArgs)
    banco.close()
    return resultado > 0L
  }

  fun listarLivros(): ArrayList<Livro> {
    val livros = ArrayList<Livro>()
    val query =
      """
      SELECT
      $TITULO_LIVRO, $AUTOR_LIVRO, $EDITORA_LIVRO, $ANO_LIVRO, $ISBN_LIVRO, $DESCRICAO_LIVRO, $URL_LIVRO
      FROM
      $TABELA_LIVROS
      ORDER BY
      $TITULO_LIVRO
      """
    val selectionArgs = null
    val banco = this.readableDatabase
    val cursor = banco.rawQuery(query, selectionArgs)
    if (cursor.count > 0) {
      cursor.moveToFirst()
      do {
        livros.add(
          Livro(
            titulo = cursor.getString(0),
            autor = cursor.getString(1),
            editora = cursor.getString(2),
            ano = cursor.getString(3),
            isbn = cursor.getString(4),
            descricao = cursor.getString(5),
            url = cursor.getString(6)
          )
        )
      } while (cursor.moveToNext())
    }
    cursor.close()
    banco.close()
    return livros
  }

  fun buscarLivro(isbn: String): Livro {
    var livro = Livro()
    val query =
      """
      SELECT
      $TITULO_LIVRO, $AUTOR_LIVRO, $EDITORA_LIVRO, $ANO_LIVRO, $ISBN_LIVRO, $DESCRICAO_LIVRO, $URL_LIVRO
      FROM
      $TABELA_LIVROS
      WHERE 
      $ISBN_LIVRO = ?
      """
    val selectionArgs = arrayOf(isbn)
    val banco = this.readableDatabase
    val cursor = banco.rawQuery(query, selectionArgs)
    if (cursor.count > 0) {
      cursor.moveToFirst()
      livro = Livro(
        titulo = cursor.getString(0),
        autor = cursor.getString(1),
        editora = cursor.getString(2),
        ano = cursor.getString(3),
        isbn = cursor.getString(4),
        descricao = cursor.getString(5),
        url = cursor.getString(6)
      )
    }
    cursor.close()
    banco.close()
    return livro
  }
}