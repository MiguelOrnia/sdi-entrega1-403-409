package com.uniovi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Conversation;
import com.uniovi.entities.Message;
import com.uniovi.entities.Sale;
import com.uniovi.entities.User;
import com.uniovi.entities.types.Role;
import com.uniovi.entities.types.SaleStatus;
import com.uniovi.pageobjects.PO_AddSale;
import com.uniovi.pageobjects.PO_BoughtView;
import com.uniovi.pageobjects.PO_HomeView;
import com.uniovi.pageobjects.PO_LoginView;
import com.uniovi.pageobjects.PO_Messages;
import com.uniovi.pageobjects.PO_MySales;
import com.uniovi.pageobjects.PO_NavView;
import com.uniovi.pageobjects.PO_Properties;
import com.uniovi.pageobjects.PO_RegisterView;
import com.uniovi.pageobjects.PO_SearchView;
import com.uniovi.pageobjects.PO_UserList;
import com.uniovi.pageobjects.PO_View;
import com.uniovi.repositories.ConversationsRepository;
import com.uniovi.repositories.MessagesRepository;
import com.uniovi.repositories.SaleRepository;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.MessagesService;
import com.uniovi.services.SaleService;
import com.uniovi.services.UsersService;
import com.uniovi.utils.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class SdiEntrega1403409ApplicationTests {

	@Autowired
	private UsersService usersService;

	@Autowired
	private SaleService saleService;

	@Autowired
	private MessagesService messagesService;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private MessagesRepository messagesRepository;

	@Autowired
	private ConversationsRepository conversationsRepository;

	// Path Miguel
//	static String PathFirefox65 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver024 = "C:\\Users\\Miguel\\Desktop\\"
//			+ "PL-SDI-Sesion5-material\\geckodriver024win64.exe";

//	// Path Emilio
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Emilio\\Documents\\SDI\\"
			+ "PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		initDb();
		driver.navigate().to(URL);
	}

	private void initDb() {
		conversationsRepository.deleteAll();
		messagesRepository.deleteAll();
		saleRepository.deleteAll();
		usersRepository.deleteAll();

		User user1 = new User("miguel@email.com", "Miguel", "Ornia Gomez",
				Role.ROLE_STAND);
		user1.setPassword("password");
		User user2 = new User("alfredo@email.com", "Alfredo", "Perez Fernandez",
				Role.ROLE_STAND);
		user2.setPassword("password");
		User user3 = new User("paco@email.com", "Paco", "Salvador Vega",
				Role.ROLE_STAND);
		user3.setPassword("password");
		User user4 = new User("maria@hotmail.es", "Maria", "Perez Hernandez",
				Role.ROLE_STAND);
		user4.setPassword("password");
		User user5 = new User("alvaro@email.com", "Alvaro", "Sanchez Vega",
				Role.ROLE_STAND);
		user5.setPassword("password");
		User admin = new User("admin@email.com", "admin", "", Role.ROLE_ADMIN);
		admin.setPassword("admin");

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(admin);

		Sale sale1 = new Sale("Coche", "Vendo BMW", 100);
		Sale sale2 = new Sale("Consola", "Vendo Play Station 5", 500);
		Sale sale3 = new Sale("Raton", "Vendo raton de ordenador", 50);
		Sale sale4 = new Sale("Joya", "Vendo pendiente de diamantes", 25000);
		sale4.setBuyer(user3);
		sale4.setStatus(SaleStatus.SOLD);

		saleService.addSale(sale1, user1);
		saleService.addSale(sale2, user2);
		saleService.addSale(sale3, user3);
		saleService.addSale(sale4, user4);

		Sale sale5 = new Sale("Botella", "Vendo botella", 25000);
		Sale sale6 = new Sale("Casa", "Vendo cas", 25000);
		sale6.setBuyer(user3);
		sale6.setStatus(SaleStatus.SOLD);
		Sale sale7 = new Sale("Boligrafo", "Vendo boligrafo azul", 25000);
		Sale sale8 = new Sale("Ordenador", "Vendo ordenador", 25000);
		Sale sale9 = new Sale("Calculadora", "Vendo calculadora", 25000);
		sale9.setBuyer(user4);
		sale9.setStatus(SaleStatus.SOLD);
		Sale sale10 = new Sale("Gafas", "Vendo gafas", 2500);
		sale10.setBuyer(user1);
		sale10.setStatus(SaleStatus.SOLD);
		Sale sale11 = new Sale("Caña de Pescar", "Vendo caña de pescar 9000",
				50);
		sale11.setBuyer(user1);
		sale11.setStatus(SaleStatus.SOLD);

		saleService.addSale(sale5, user1);
		saleService.addSale(sale6, user1);
		saleService.addSale(sale7, user1);
		saleService.addSale(sale8, user1);
		saleService.addSale(sale9, user1);
		saleService.addSale(sale10, user4);
		saleService.addSale(sale11, user4);

		// Adding Sales
		Sale s1 = new Sale("Vendo Ford Fiesta", "En perfecto estado", 4560.2);
		Sale s2 = new Sale("Vendo iPhone 4", "Con arañazos", 120);
		Sale s3 = new Sale("Vendo Play 3", "En perfecto estado", 80);
		saleService.addSale(s1, user1);
		saleService.addSale(s2, user1);
		saleService.addSale(s3, user1);

		Sale s12 = new Sale("Vendo Cosa Random 1", "En perfecto estado",
				4560.2);
		Sale s22 = new Sale("Vendo Cosa Random 2", "Con arañazos", 120);
		Sale s32 = new Sale("Vendo Cosa Random 3", "En perfecto estado", 80);
		saleService.addSale(s12, user2);
		saleService.addSale(s22, user2);
		saleService.addSale(s32, user2);

		Sale s13 = new Sale("Vendo Cosa Random 4", "En perfecto estado",
				4560.2);
		Sale s23 = new Sale("Vendo Cosa Random 5", "Con arañazos", 120);
		Sale s33 = new Sale("Vendo Cosa Random 6", "En perfecto estado", 80);
		saleService.addSale(s13, user3);
		saleService.addSale(s23, user3);
		saleService.addSale(s33, user3);

		Sale s14 = new Sale("Vendo Cosa Random 7", "En perfecto estado",
				4560.2);
		Sale s24 = new Sale("Vendo Cosa Random 8", "Con arañazos", 120);
		Sale s34 = new Sale("Vendo Cosa Random 9", "En perfecto estado", 80);
		saleService.addSale(s14, user4);
		saleService.addSale(s24, user4);
		saleService.addSale(s34, user4);

		Sale s15 = new Sale("Vendo Cosa Random 10", "En perfecto estado",
				4560.2);
		Sale s25 = new Sale("Vendo Cosa Random 11", "Con arañazos", 120);
		Sale s35 = new Sale("Vendo Cosa Random 12", "En perfecto estado", 80);
		saleService.addSale(s15, user5);
		saleService.addSale(s25, user5);
		saleService.addSale(s35, user5);

		// Adding conversations
		Conversation chat11 = messagesService.getConversation(user1, s12);
		Conversation chat12 = messagesService.getConversation(user1, s22);
		Conversation chat13 = messagesService.getConversation(user1, s32);

		Conversation chat21 = messagesService.getConversation(user2, s13);
		Conversation chat22 = messagesService.getConversation(user2, s23);
		Conversation chat23 = messagesService.getConversation(user2, s33);

		Conversation chat31 = messagesService.getConversation(user3, s14);
		Conversation chat32 = messagesService.getConversation(user3, s24);
		Conversation chat33 = messagesService.getConversation(user3, s34);

		Conversation chat41 = messagesService.getConversation(user4, s15);
		Conversation chat42 = messagesService.getConversation(user4, s25);
		Conversation chat43 = messagesService.getConversation(user4, s35);

		Conversation chat51 = messagesService.getConversation(user5, s1);
		Conversation chat52 = messagesService.getConversation(user5, s2);
		Conversation chat53 = messagesService.getConversation(user5, s3);

		// Adding messages
		Message m111 = new Message(user1, "Buenos días");
		Message m112 = new Message(user1, "Quería información sobre la oferta");
		Message m113 = new Message(user2, "Buenos días");
		Message m114 = new Message(user2, "Qué quiere saber?");
		messagesService.addMessage(m111, chat11);
		messagesService.addMessage(m112, chat11);
		messagesService.addMessage(m113, chat11);
		messagesService.addMessage(m114, chat11);
		Message m121 = new Message(user1, "Buenos días");
		Message m122 = new Message(user1, "Quería información sobre la oferta");
		Message m123 = new Message(user2, "Buenos días");
		Message m124 = new Message(user2, "Qué quiere saber?");
		messagesService.addMessage(m121, chat12);
		messagesService.addMessage(m122, chat12);
		messagesService.addMessage(m123, chat12);
		messagesService.addMessage(m124, chat12);
		Message m131 = new Message(user1, "Buenos días");
		Message m132 = new Message(user1, "Quería información sobre la oferta");
		Message m133 = new Message(user2, "Buenos días");
		Message m134 = new Message(user2, "Qué quiere saber?");
		messagesService.addMessage(m131, chat13);
		messagesService.addMessage(m132, chat13);
		messagesService.addMessage(m133, chat13);
		messagesService.addMessage(m134, chat13);

		Message m211 = new Message(user2, "Buenos días");
		Message m212 = new Message(user2, "Quería información sobre la oferta");
		Message m213 = new Message(user3, "Buenos días");
		Message m214 = new Message(user3, "Qué quiere saber?");
		messagesService.addMessage(m211, chat21);
		messagesService.addMessage(m212, chat21);
		messagesService.addMessage(m213, chat21);
		messagesService.addMessage(m214, chat21);
		Message m221 = new Message(user2, "Buenos días");
		Message m222 = new Message(user2, "Quería información sobre la oferta");
		Message m223 = new Message(user3, "Buenos días");
		Message m224 = new Message(user3, "Qué quiere saber?");
		messagesService.addMessage(m221, chat22);
		messagesService.addMessage(m222, chat22);
		messagesService.addMessage(m223, chat22);
		messagesService.addMessage(m224, chat22);
		Message m231 = new Message(user2, "Buenos días");
		Message m232 = new Message(user2, "Quería información sobre la oferta");
		Message m233 = new Message(user3, "Buenos días");
		Message m234 = new Message(user3, "Qué quiere saber?");
		messagesService.addMessage(m231, chat23);
		messagesService.addMessage(m232, chat23);
		messagesService.addMessage(m233, chat23);
		messagesService.addMessage(m234, chat23);

		Message m311 = new Message(user3, "Buenos días");
		Message m312 = new Message(user3, "Quería información sobre la oferta");
		Message m313 = new Message(user4, "Buenos días");
		Message m314 = new Message(user4, "Qué quiere saber?");
		messagesService.addMessage(m311, chat31);
		messagesService.addMessage(m312, chat31);
		messagesService.addMessage(m313, chat31);
		messagesService.addMessage(m314, chat31);
		Message m321 = new Message(user3, "Buenos días");
		Message m322 = new Message(user3, "Quería información sobre la oferta");
		Message m323 = new Message(user4, "Buenos días");
		Message m324 = new Message(user4, "Qué quiere saber?");
		messagesService.addMessage(m321, chat32);
		messagesService.addMessage(m322, chat32);
		messagesService.addMessage(m323, chat32);
		messagesService.addMessage(m324, chat32);
		Message m331 = new Message(user3, "Buenos días");
		Message m332 = new Message(user3, "Quería información sobre la oferta");
		Message m333 = new Message(user4, "Buenos días");
		Message m334 = new Message(user4, "Qué quiere saber?");
		messagesService.addMessage(m331, chat33);
		messagesService.addMessage(m332, chat33);
		messagesService.addMessage(m333, chat33);
		messagesService.addMessage(m334, chat33);

		Message m411 = new Message(user4, "Buenos días");
		Message m412 = new Message(user4, "Quería información sobre la oferta");
		Message m413 = new Message(user5, "Buenos días");
		Message m414 = new Message(user5, "Qué quiere saber?");
		messagesService.addMessage(m411, chat41);
		messagesService.addMessage(m412, chat41);
		messagesService.addMessage(m413, chat41);
		messagesService.addMessage(m414, chat41);
		Message m421 = new Message(user4, "Buenos días");
		Message m422 = new Message(user4, "Quería información sobre la oferta");
		Message m423 = new Message(user5, "Buenos días");
		Message m424 = new Message(user5, "Qué quiere saber?");
		messagesService.addMessage(m421, chat42);
		messagesService.addMessage(m422, chat42);
		messagesService.addMessage(m423, chat42);
		messagesService.addMessage(m424, chat42);
		Message m431 = new Message(user4, "Buenos días");
		Message m432 = new Message(user4, "Quería información sobre la oferta");
		Message m433 = new Message(user5, "Buenos días");
		Message m434 = new Message(user5, "Qué quiere saber?");
		messagesService.addMessage(m431, chat43);
		messagesService.addMessage(m432, chat43);
		messagesService.addMessage(m433, chat43);
		messagesService.addMessage(m434, chat43);

		Message m511 = new Message(user5, "Buenos días");
		Message m512 = new Message(user5, "Quería información sobre la oferta");
		Message m513 = new Message(user1, "Buenos días");
		Message m514 = new Message(user1, "Qué quiere saber?");
		messagesService.addMessage(m511, chat51);
		messagesService.addMessage(m512, chat51);
		messagesService.addMessage(m513, chat51);
		messagesService.addMessage(m514, chat51);
		Message m521 = new Message(user5, "Buenos días");
		Message m522 = new Message(user5, "Quería información sobre la oferta");
		Message m523 = new Message(user1, "Buenos días");
		Message m524 = new Message(user1, "Qué quiere saber?");
		messagesService.addMessage(m521, chat52);
		messagesService.addMessage(m522, chat52);
		messagesService.addMessage(m523, chat52);
		messagesService.addMessage(m524, chat52);
		Message m531 = new Message(user5, "Buenos días");
		Message m532 = new Message(user5, "Quería información sobre la oferta");
		Message m533 = new Message(user1, "Buenos días");
		Message m534 = new Message(user1, "Qué quiere saber?");
		messagesService.addMessage(m531, chat53);
		messagesService.addMessage(m532, chat53);
		messagesService.addMessage(m533, chat53);
		messagesService.addMessage(m534, chat53);

	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR01. Registro de Usuario con datos válidos
	@Test
	public void PR01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "underlineHover");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "test@email.com", "test_", "test_",
				"123456", "123456");
		// Comprobamos que entramos en la sección privada
		PO_RegisterView.checkKey(driver, "welcome.message",
				PO_Properties.getSPANISH());
	}

	// PR02. Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos)
	@Test
	public void PR02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "underlineHover");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, " ", "test_", "test_", "123456",
				"123456");
		// Comprobamos el error de email vacío.
		PO_RegisterView.checkKey(driver, "Error.signup.email.length",
				PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "test@email.com", " ", "test_",
				"123456", "123456");
		// Comprobamos el error de nombre vacío.
		PO_RegisterView.checkKey(driver, "Error.signup.name.length",
				PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "test@email.com", "test_", " ",
				"123456", "123456");
		// Comprobamos el error de apellidos vacío.
		PO_RegisterView.checkKey(driver, "Error.signup.surname.length",
				PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "test@email.com", "test_", "test_",
				" ", " ");
		// Comprobamos el error de contraseña vacía.
		PO_RegisterView.checkKey(driver, "Error.signup.password.length",
				PO_Properties.getSPANISH());
	}

	// PR03. Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida).
	@Test
	public void PR03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "underlineHover");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "test@email.com", "test", "test",
				"123456", "123457");
		// Comprobamos que el error existe
		PO_RegisterView.checkKey(driver, "Error.signup.repassword.coincidence",
				PO_Properties.getSPANISH());
	}

	// PR04. Registro de Usuario con datos inválidos (email existente).
	@Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "underlineHover");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "miguel@email.com", "test", "test",
				"123456", "123456");
		// Comprobamos el error de email repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate",
				PO_Properties.getSPANISH());
	}

	// PR05. Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR05() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Comprobamos que es el admin
		PO_NavView.checkIsAdmin(driver);
	}

	// PR06. Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR06() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		// Comprobamos que es el admin
		PO_NavView.checkIsUser(driver);
	}

	// PR07. Inicio de sesión con datos inválidos (usuario estándar, campo email
	// y
	// contraseña vacíos).
	@Test
	public void PR07() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "   ", "   ");
		// Comprobamos que el error existe
		PO_LoginView.checkKey(driver, "login.error",
				PO_Properties.getSPANISH());
	}

	// PR08. Inicio de sesión con datos válidos (usuario estándar, email
	// existente,
	// pero contraseña incorrecta).
	@Test
	public void PR08() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "123456789");
		// Comprobamos que el error existe
		PO_LoginView.checkKey(driver, "login.error",
				PO_Properties.getSPANISH());
	}

	// PR09. Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	@Test
	public void PR09() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "noexisto@email.com", "123456");
		// Comprobamos que el error existe
		PO_LoginView.checkKey(driver, "login.error",
				PO_Properties.getSPANISH());
	}

	// PR10. Hacer click en la opción de salir de sesión y comprobar que se
	// redirige
	// a la página de inicio de sesión (Login).
	@Test
	public void PR10() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		// Salimos de sesión
		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id, 'desconexion')]/a");
		elementos.get(0).click();
		// Comprobamos que entramos en la página de login
		PO_LoginView.checkElement(driver, "id", "login");
	}

	// PR11. Comprobar que el botón cerrar sesión no está visible si el usuario
	// no
	// está autenticado.
	@Test
	public void PR11() {
		PO_View.checkNoKey(driver, "logout.message",
				PO_Properties.getSPANISH());
	}

	// PR12. Mostrar el listado de usuarios y comprobar que se muestran todos
	// los
	// que existen en el sistema.
	@Test
	public void PR12() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Vamos a la lista de usuarios
		PO_UserList.goToPage(driver);
		// Conseguimos los usuarios
		List<WebElement> elementos = PO_UserList.checkElement(driver, "class",
				"checkBox");
		assertTrue(elementos.size() == 5);
		PO_UserList.checkElement(driver, "text", "miguel@email.com");
		PO_UserList.checkElement(driver, "text", "alfredo@email.com");
		PO_UserList.checkElement(driver, "text", "paco@email.com");
		PO_UserList.checkElement(driver, "text", "maria@hotmail.es");
		PO_UserList.checkElement(driver, "text", "alvaro@email.com");
	}

	// PR13. Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	@Test
	public void PR13() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Vamos a la lista de usuarios
		PO_UserList.goToPage(driver);
		// Conseguimos los usuarios
		PO_UserList.deleteUser(driver, 0);
		List<WebElement> elementos = PO_UserList.checkElement(driver, "class",
				"checkBox");
		assertTrue(elementos.size() == 4);
		PO_UserList.checkElement(driver, "text", "alfredo@email.com");
		PO_UserList.checkElement(driver, "text", "paco@email.com");
		PO_UserList.checkElement(driver, "text", "maria@hotmail.es");
		PO_UserList.checkElement(driver, "text", "alvaro@email.com");
	}

	// PR14. Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	@Test
	public void PR14() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Vamos a la lista de usuarios
		PO_UserList.goToPage(driver);
		// Conseguimos los usuarios
		PO_UserList.deleteUser(driver, 4);
		List<WebElement> elementos = PO_UserList.checkElement(driver, "class",
				"checkBox");
		assertTrue(elementos.size() == 4);
		PO_UserList.checkElement(driver, "text", "miguel@email.com");
		PO_UserList.checkElement(driver, "text", "alfredo@email.com");
		PO_UserList.checkElement(driver, "text", "paco@email.com");
		PO_UserList.checkElement(driver, "text", "maria@hotmail.es");
	}

	// PR15. Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la
	// lista se
	// actualiza y dichos usuarios desaparecen.
	@Test
	public void PR15() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Vamos a la lista de usuarios
		PO_UserList.goToPage(driver);
		// Conseguimos los usuarios
		PO_UserList.deleteUser(driver, 0, 1, 2);
		List<WebElement> elementos = PO_UserList.checkElement(driver, "class",
				"checkBox");
		assertTrue(elementos.size() == 2);
		PO_UserList.checkElement(driver, "text", "maria@hotmail.es");
		PO_UserList.checkElement(driver, "text", "alvaro@email.com");
	}

	// PR16.Ir al formulario de alta de oferta, rellenarla con datos válidos y
	// pulsar el botón Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void PR16() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		// Vamos a la pagina
		PO_AddSale.goToPage(driver);
		// Rellenar
		PO_AddSale.addSale(driver, "Test", "Oferta para testear", 100.0);
		// Comprobar que ha sido añadida
		PO_MySales.goToPage(driver);
		PO_MySales.checkElement(driver, "text", "Test");
		PO_MySales.checkElement(driver, "text", "Oferta para testear");
	}

	// PR17.Ir al formulario de alta de oferta, rellenarla con datos inválidos
	// (campo título vacío) y pulsar
	// el botón Submit. Comprobar que se muestra el mensaje de campo
	// obligatorio.
	@Test
	public void PR17() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		// Vamos a la pagina
		PO_AddSale.goToPage(driver);
		// Rellenar
		PO_AddSale.addSale(driver, " ", "Oferta", 80.0);
		PO_AddSale.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		PO_AddSale.addSale(driver, "Test", " ", 80.0);
		PO_AddSale.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		PO_AddSale.addSale(driver, "Test", "Oferta", -200.0);
		PO_AddSale.checkKey(driver, "Error.addSale.price.value",
				PO_Properties.getSPANISH());
	}

	// PR18. Mostrar el listado de ofertas para dicho usuario y comprobar que se
	// muestran todas los que existen para este usuario.
	@Test
	public void PR18() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");

		// Nos dirigimos a la lista de nuestras Ofertas
		PO_MySales.goToPage(driver);

	}

	// PR19. Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y que la oferta desaparece.
	@Test
	public void PR19() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");

		// Nos dirigimos a la lista de nuestras Ofertas
		PO_MySales.goToPage(driver);

		// Eliminamos la primera oferta
		// Devuelve true en caso de que se haya eliminado dicha oferta
		assertTrue(PO_MySales.deleteItem(driver, 0));

	}

	// PR20. Ir a la lista de ofertas, borrar la última oferta de la lista,
	// comprobar que la lista se actualiza y que la oferta desaparece.
	@Test
	public void PR20() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");

		// Nos dirigimos a la lista de nuestras Ofertas
		PO_MySales.goToPage(driver);

		// Cogemos el indice del ultimo objeto eliminable
		int lastIndex = PO_MySales.checkNumberOfDeleteableItems(driver) - 1;

		// Eliminamos Ultima oferta y comprobamos que los elementos se
		// redujeron.
		assertTrue(PO_MySales.deleteItem(driver, lastIndex));

	}

	// PR21. Hacer una búsqueda con el campo vacío y comprobar que se muestra la
	// página que
	// corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void PR21() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_SearchView.goToPage(driver);
		// Buscar
		PO_SearchView.searchForSale(driver, "");
		// Comprobar las ofertas
		List<WebElement> sale = PO_SearchView.checkElement(driver, "class",
				"table-light");
		assertTrue(sale.size() == 2);
		SeleniumUtils.textoPresentePagina(driver, "Consola");
		SeleniumUtils.textoPresentePagina(driver, "Raton");
	}

	// PR22. Hacer una búsqueda escribiendo en el campo un texto que no exista y
	// comprobar que se
	// muestra la página que corresponde, con la lista de ofertas vacía.
	@Test
	public void PR22() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_SearchView.goToPage(driver);
		// Buscar
		PO_SearchView.searchForSale(driver, "noExisto");
		// Comprobar las ofertas
		SeleniumUtils.textoNoPresentePagina(driver, "Consola");
		SeleniumUtils.textoNoPresentePagina(driver, "Raton");
	}

	// PR23. Sobre una búsqueda determinada, comprar una oferta que deja
	// un saldo positivo en el contador del comprobador. Y comprobar que el
	// contador
	// se actualiza
	// correctamente en la vista del comprador.
	@Test
	public void PR23() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_SearchView.goToPage(driver);
		// Buscar
		PO_SearchView.searchForSale(driver, "Raton");
		// Comprar la oferta
		PO_SearchView.buyOffer(driver, "Raton");
		// Comprobar balance
		PO_HomeView.goToPage(driver);
		double balance = PO_HomeView.getUserBalance(driver);
		assertEquals(50.0, balance, 0.1);
	}

	// PR24. Sobre una búsqueda determinada (a elección de desarrollador),
	// comprar
	// una oferta que deja
	// un saldo 0 en el contador del comprobador. Y comprobar que el contador se
	// actualiza correctamente en
	// la vista del comprador.
	@Test
	public void PR24() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_SearchView.goToPage(driver);
		// Buscar
		PO_SearchView.searchForSale(driver, "Coche");
		// Comprar la oferta
		PO_SearchView.buyOffer(driver, "Coche");
		// Comprobar balance
		PO_HomeView.goToPage(driver);
		double balance = PO_HomeView.getUserBalance(driver);
		assertEquals(0.0, balance, 0.1);
	}

	// PR25. Sobre una búsqueda determinada (a elección de desarrollador),
	// intentar
	// comprar una oferta
	// que esté por encima de saldo disponible del comprador. Y comprobar que se
	// muestra el mensaje de
	// saldo no suficiente.
	@Test
	public void PR25() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_SearchView.goToPage(driver);
		// Buscar
		PO_SearchView.searchForSale(driver, "Consola");
		// Comprar la oferta
		PO_SearchView.buyOffer(driver, "Consola");
		// Comprobar balance
		PO_HomeView.checkKey(driver, "sale.buy.error",
				PO_Properties.getSPANISH());
	}

	// PR26. Ir a la opción de ofertas compradas del usuario y mostrar la lista.
	// Comprobar que aparecen las ofertas que deben aparecer.
	@Test
	public void PR26() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_BoughtView.goToPage(driver);
		assertEquals(2, PO_BoughtView.checkNumberOfItems(driver));
	}

	// PR27. Internalización
	@Test
	public void PR27() {
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		// Comprobamos las vistas de las paginas
		PO_HomeView.goToPage(driver);
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",
				PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		PO_NavView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",
				PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		PO_AddSale.goToPage(driver);
		PO_AddSale.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",
				PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		// Como admin
		List<WebElement> elementos = PO_View.checkElement(driver, "free",
				"//li[contains(@id, 'desconexion')]/a");
		elementos.get(0).click();
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_UserList.goToPage(driver);
		PO_UserList.checkChangeIdiom(driver, "btnSpanish", "btnEnglish",
				PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
	}

	// PR28. Intentar acceder sin estar autenticado a la opción de listado de
	// usuarios del administrador. Se deberá volver al formulario de login.
	@Test
	public void PR28() {
		// Vamos a la pagina
		driver.navigate().to(URL + "/user/list/");
		SeleniumUtils.esperarSegundos(driver, 3);
		// Comprobamos que entramos en la página de login
		PO_LoginView.checkElement(driver, "id", "login");
	}

	// PR29. Intentar acceder sin estar autenticado a la opción de listado de
	// ofertas propias de un usuario estándar. Se deberá volver al formulario de
	// login.
	@Test
	public void PR29() {
		// Vamos a la pagina
		driver.navigate().to(URL + "/sale/list/");
		SeleniumUtils.esperarSegundos(driver, 2);
		// Comprobamos que entramos en la página de login
		PO_LoginView.checkElement(driver, "id", "login");
	}

//	// PR30. Estando autenticado como usuario estándar intentar acceder a la
//	// opción
//	// de listado de usuarios del administrador. Se deberá indicar un mensaje de
//	// acción prohibida.
//	@Test
//	public void PR30() {
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
//		driver.navigate().to(URL + "/user/list/");
//		PO_LoginView.checkKey(driver, "error.notAuthorized",
//				PO_Properties.getSPANISH());
//	}
	
	/**
	 * Sobre una búsqueda determinada de ofertas (a elección de desarrollador),
	 * enviar un mensaje a una oferta concreta. Se abriría dicha conversación
	 * por primera vez. Comprobar que el mensaje aparece en el listado de
	 * mensajes.
	 */
	@Test
	public void PR31() {
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_SearchView.goToPage(driver);
		// Realizo una búsqueda de ofertas
		PO_SearchView.searchForSale(driver, "Consola");
		// Inicio una nueva conversación con la primera oferta
		PO_SearchView.sendMessageToSale(driver, "Consola");
		// Envío un mensaje en la conversación
		PO_Messages.sendMessage(driver, "Hola");
		List<WebElement> elementos = PO_Messages.checkElement(driver, "class",
				"sent");
		// Compruebo que tan solo hay un mensaje enviado en la conversación
		assertTrue(elementos.size() == 1);

	}

	/**
	 * Sobre el listado de conversaciones enviar un mensaje a una conversación
	 * ya abierta. Comprobar que el mensaje aparece en la lista de mensajes.
	 */
	@Test
	public void PR32() {
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_Messages.goToPage(driver);
		// Vamos al chat con id 17
		PO_Messages.goToChat(driver, "17");
		List<WebElement> elementos = PO_Messages.checkElement(driver, "class",
				"sent");
		// Compruebo que ya había dos mensajes enviados en la conversación
		assertTrue(elementos.size() == 2);
		PO_Messages.sendMessage(driver, "Hola");
		// Compruebo que ahora hay un mensaje más enviado en la conversación
		elementos = PO_Messages.checkElement(driver, "class", "sent");
		assertTrue(elementos.size() == 3);
	}

	/**
	 * Mostrar el listado de conversaciones ya abiertas. Comprobar que el
	 * listado contiene las conversaciones que deben ser.
	 */
	@Test
	public void PR33() {
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_Messages.goToPage(driver);
		List<WebElement> elementos = PO_Messages.checkElement(driver, "class",
				"chatCard");
		// Compruebo que tiene un chat por cada oferta de los otros usuarios (5)
		// y otro chat por su propia oferta
		assertTrue(elementos.size() == 6);
	}

	/**
	 * Sobre el listado de conversaciones ya abiertas. Pinchar el enlace
	 * Eliminar de la primera y comprobar que el listado se actualiza
	 * correctamente.
	 */
	@Test
	public void PR34() {
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_Messages.goToPage(driver);
		List<WebElement> elementos = PO_Messages.checkElement(driver, "class",
				"chatCard");
		// Compruebo que tiene un chat por cada oferta de los otros usuarios (5)
		// y otro chat por su propia oferta
		assertTrue(elementos.size() == 6);
		// Elimino el primer mensaje
		PO_Messages.deleteFirstMessage(driver);
		// Compruebo que hay una conversación menos
		elementos = PO_Messages.checkElement(driver, "class",
				"chatCard");
		assertTrue(elementos.size() == 5);
	}

	/**
	 * Sobre el listado de conversaciones ya abiertas. Pinchar el enlace
	 * Eliminar de la última y comprobar que el listado se actualiza
	 * correctamente.
	 */
	@Test
	public void PR35() {
		PO_LoginView.fillForm(driver, "miguel@email.com", "password");
		PO_Messages.goToPage(driver);
		List<WebElement> elementos = PO_Messages.checkElement(driver, "class",
				"chatCard");
		// Compruebo que tiene un chat por cada oferta de los otros usuarios (5)
		// y otro chat por su propia oferta
		assertTrue(elementos.size() == 6);
		// Elimino el primer mensaje
		PO_Messages.deleteLastMessage(driver);
		// Compruebo que hay una conversación menos
		elementos = PO_Messages.checkElement(driver, "class",
				"chatCard");
		assertTrue(elementos.size() == 5);
	}
	
	
}
