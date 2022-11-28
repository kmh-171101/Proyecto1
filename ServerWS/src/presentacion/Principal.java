package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import logica.Fabrica;
import logica.IATYST;
import logica.IDepartamento;
import logica.IPaquete;
import logica.IUsuario;
import servidor.Publicador;
import excepciones.ExcedeCapacidadException;
import excepciones.ExisteActividadException;
import excepciones.ExisteCategoriaException;
import excepciones.NoExisteDepartamentoException;
import excepciones.NoExisteProveedorException;
import excepciones.YaTieneInscripcionAEstaSalidaException;

import excepciones.ExistePaqueteException;



import java.time.LocalDate;
import java.time.LocalTime;


public class Principal{
	//Este es el nombre de la ventana principal
	private JFrame frmTurismoUy;
    
	// la interfaz para Actividades
	private IATYST IAT;
	private IDepartamento IDep;
	private IUsuario IUsr;
	private IPaquete IPaq;
	
	private AltaActividadTuristica altAcInternalFrame;

	private ConsultaDeSalida ConsSalidaInternalFrame;
	private CrearPaqueteDeActividadTuristica crerPaqueteInternalFrame;
	private AgregarActividadAPaquete agrActPaqInternalFrame;

	private AltaUsuarioCorrecto altUsrInternalFrame;
	private ConsultaUsuario ConsUsrInternalFrame;
	private ModificarDatosDeUsuario ModUsrInternalFrame;

	private InscripcionASalidaTuristica incSalTurInternalFrame;
	private AltaSalidaTuristica AltaSalidaInternalFrame;
	

	private ConsultaActividadTuristica ConsultaATInternalFrame;
	private ConsultaDePaqueteDeActividadTuristica ConsultaPaqATInternalFrame;
	private AltaCategoria AltaCatInternalFrame;
	private AceptarRechazarAT acepRechActInternalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frmTurismoUy.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

	/**
	 * Create the frame.
	 */
	public Principal() {
		inicializar();
		
		Fabrica fabrica = Fabrica.getInstance();
	    IAT = fabrica.getIATYST();
	    IDep= fabrica.getIDepartamento();
	    IUsr= fabrica.getIUsuario();
	    IPaq= fabrica.getIPaquete();
        frmTurismoUy.getContentPane().setLayout(null);
        

	    ConsSalidaInternalFrame = new ConsultaDeSalida(IAT, IDep);
	    ConsSalidaInternalFrame.setBounds(335, 206, 573, 248);
        ConsSalidaInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().add(ConsSalidaInternalFrame);
        
	    ModUsrInternalFrame = new ModificarDatosDeUsuario(IUsr);
	    ModUsrInternalFrame.setBounds(356, 171, 531, 319);
        ModUsrInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().add(ModUsrInternalFrame);
        
	    ConsUsrInternalFrame = new ConsultaUsuario(IUsr, IAT);
	    ConsUsrInternalFrame.setBounds(400, 196, 444, 269);
        ConsUsrInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().add(ConsUsrInternalFrame);
        
        crerPaqueteInternalFrame =new CrearPaqueteDeActividadTuristica(IPaq);
        crerPaqueteInternalFrame.setBounds(280, 206, 683, 248);
        crerPaqueteInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().add(crerPaqueteInternalFrame);
        
        incSalTurInternalFrame= new InscripcionASalidaTuristica(IAT,IDep,IUsr);
        incSalTurInternalFrame.setBounds(245, 206, 754, 248);
        incSalTurInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().add(incSalTurInternalFrame);
        
        AltaSalidaInternalFrame = new AltaSalidaTuristica(IDep, IAT);
        AltaSalidaInternalFrame.setLocation(397, 180);
        AltaSalidaInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().add(AltaSalidaInternalFrame);
        

        altUsrInternalFrame = new AltaUsuarioCorrecto(IUsr);
        altUsrInternalFrame.setBounds(69, 161, 893, 388);
        frmTurismoUy.getContentPane().add(altUsrInternalFrame);
        altUsrInternalFrame.setVisible(false);
                
        
        agrActPaqInternalFrame = new AgregarActividadAPaquete(IPaq, IDep);
        agrActPaqInternalFrame.setBounds(31, 161, 893, 388);
        frmTurismoUy.getContentPane().add(agrActPaqInternalFrame);
        agrActPaqInternalFrame.setVisible(false);
        
        ConsultaPaqATInternalFrame = new ConsultaDePaqueteDeActividadTuristica(IPaq, IAT);
        ConsultaPaqATInternalFrame.setVisible(false);
        ConsultaPaqATInternalFrame.setBounds(31, 102, 893, 388);
        frmTurismoUy.getContentPane().add(ConsultaPaqATInternalFrame);
        
        ConsultaATInternalFrame = new ConsultaActividadTuristica(IAT, IDep, IPaq,ConsultaPaqATInternalFrame, ConsSalidaInternalFrame);
        ConsultaPaqATInternalFrame.setConsultaActividad(ConsultaATInternalFrame);
        ConsultaATInternalFrame.setBounds(224, 202, 893, 388);
        frmTurismoUy.getContentPane().add(ConsultaATInternalFrame);
        ConsultaATInternalFrame.setVisible(false);
        
        altAcInternalFrame = new AltaActividadTuristica(IAT, IDep, IUsr);
        altAcInternalFrame.setBounds(410, 182, 683, 367);
        altAcInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().setLayout(null);
        frmTurismoUy.getContentPane().add(altAcInternalFrame);
        
        ConsUsrInternalFrame.setActividadSalida(ConsultaATInternalFrame,ConsSalidaInternalFrame );
        
        AltaCatInternalFrame = new AltaCategoria(IAT);
        AltaCatInternalFrame.setBounds(201, 66, 424, 170);
        AltaCatInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().setLayout(null);
        frmTurismoUy.getContentPane().add(AltaCatInternalFrame);
        
        acepRechActInternalFrame = new AceptarRechazarAT(IAT);
        acepRechActInternalFrame.setBounds(280, 26, 628, 170);
        acepRechActInternalFrame.setVisible(false);
        frmTurismoUy.getContentPane().setLayout(null);
        frmTurismoUy.getContentPane().add(acepRechActInternalFrame);
	}
	

	private void inicializar() {
		
		//levanto el web service con los web methodos
	    Publicador p = new Publicador();
        p.publicar();
		
		frmTurismoUy = new JFrame();
		frmTurismoUy.setTitle("Turismo Uruguay");
		frmTurismoUy.setBounds(100, 100, 1258, 720);
		frmTurismoUy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTurismoUy.setJMenuBar(menuBar);
		
		JMenu menuSistema = new JMenu("Sistema");
        menuBar.add(menuSistema);

        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicaciÃ³n
                frmTurismoUy.setVisible(false);
                frmTurismoUy.dispose();
            }
        });
        menuSistema.add(menuSalir);
        JMenuItem menuCargarDatos = new JMenuItem("Cargar Datos");
        menuCargarDatos.addActionListener(new ActionListener() {
        	boolean ya_cargo=false;
            public void actionPerformed(ActionEvent arg0) {
            	if(!ya_cargo) {
            		ya_cargo=true;
            		try {
        				IAT.darAltaCategoria("Aventura y Deporte");
        				IAT.darAltaCategoria("Campo y Naturaleza");
        				IAT.darAltaCategoria("Cultura y Patrimonio");
        				IAT.darAltaCategoria("Gastronomia");
        				IAT.darAltaCategoria("Turismo Playas");
        			} catch (ExisteCategoriaException e) {
        				e.printStackTrace();
        			}
        			
        			
        			IDep.darAltaDepartamentoCargarDatos("Artigas", "Division Turismo de la Intendencia", "http://www.artigas.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Canelones", "Division Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
        			IDep.darAltaDepartamentoCargarDatos("Montevideo", "Division Turismo de la Intendencia", "https://montevideo.gub.uy/areas-tematicas/turismo");
        			IDep.darAltaDepartamentoCargarDatos("Rocha", "La Organizacion de Gestion del Destino (OGD) Rocha es un ambito de articulacion publico – privada en el sector turıstico que integran la Corporacion Rochense de Turismo y la Intendencia de Rocha a traves de su Direccion de Turismo", "http://www.turismorocha.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectaculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio mundial. Todo el a˜no se disfruta.", "https://colonia.gub.uy/turismo/");
        			IDep.darAltaDepartamentoCargarDatos("San Jose", "Division Turismo de la Intendencia", "https://sanjose.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Maldonado", "Division Turismo de la Intendencia", "https://www.maldonado.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Rio Negro", "Division Turismo de la Intendencia", "https://www.rionegro.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Cerro Largo", "Division Turismo de la Intendencia", "https://www.gub.uy/intendencia-cerro-largo/");
        			IDep.darAltaDepartamentoCargarDatos("Tacuarembo", "Division Turismo de la Intendencia", "https://tacuarembo.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Rivera", "Promociona e implementa proyectos e iniciativas sostenibles de interes turistico con la participacion institucional publica – privada en bien del desarrollo socioeconomico de la comunidad.", "http://www.rivera.gub.uy/social/turismo/");
        			IDep.darAltaDepartamentoCargarDatos("Treinta y Tres", "Division Turismo de la Intendencia", "https://treintaytres.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Salto", "Division Turismo de la Intendencia", "https://www.salto.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Paysandu", "Division Turismo de la Intendencia", "https://www.paysandu.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Florida", "Division Turismo de la Intendencia", "http://www.florida.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Flores", "Division Turismo de la Intendencia", "https://flores.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Durazno", "Division Turismo de la Intendencia", "https://durazno.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Soriano", "Division Turismo de la Intendencia", "https://www.soriano.gub.uy/");
        			IDep.darAltaDepartamentoCargarDatos("Lavalleja", "Division Turismo de la Intendencia", "http://www.lavalleja.gub.uy/");
        			

                	IUsr.darAltaTurista("lachiqui","Rosa María", "Martinez", "mirtha.legrand.ok@hotmail.com.ar ",LocalDate.of(1927,2,23), "argentina","awdrg543" /**/);
                	IUsr.darAltaTurista("isabelita","Elizabeth", "Windsor", "isabelita@thecrown.co.uk",LocalDate.of(1926,4,21), "inglesa","r5t6y7u8" /**/);
                	IUsr.darAltaTurista("anibal","Anibal","Lecter","anibal@fing.edu.uy",LocalDate.of(1937,12,31), "lituana","edrft543" /**/);
                	IUsr.darAltaTurista("waston","Emma", "Waston", "e.waston@gmail.com",LocalDate.of(1990,4,15), "inglesa","poiuy987" /**/);
                	IUsr.darAltaTurista("elelvis","Elvis", "Lacio", "suavemente@hotmail.com",LocalDate.of(1971,7,30), "estadounidense","45idgaf67" /**/);
                	IUsr.darAltaTurista("eleven11","Eleven", "Once", "eleven11@gmail.com", LocalDate.of(2004,2,19), "española","xdrgb657" /**/);
                	IUsr.darAltaTurista("bobesponja","Bob", "Esponja", "bobesponja@nickelodeon.com", LocalDate.of(1999,5,01), "japonesa","sbsplol1" /**/);
                	IUsr.darAltaTurista("tony","Antonio", "Pacheco", "eltony@manya.org.uy", LocalDate.of(1976,04,11), "uruguaya","okmnji98" /**/);
                	IUsr.darAltaTurista("chino","Alvaro", "Recoba", "chino@trico.org.uy",LocalDate.of(1976,03,17), "uruguaya","qsxcdw43" /**/);
                	IUsr.darAltaTurista("mastropiero","Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com",LocalDate.of(1922,2,07), "austriaca","qpwoei586" /**/);
                	IUsr.darAltaProveedor("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", LocalDate.of(1970,9,14), "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha Uruguay", "http://turismorocha.gub.uy/","asdfg654" /**/);
                	IUsr.darAltaProveedor("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", LocalDate.of(1965,6,27), "Pablo es el presidente de la Sociedad de Fomento Turistico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy","*ytrewq10" /**/);
                	IUsr.darAltaProveedor("meche", "Mercedes", "Venn", "meche@colonia.gub.uy",LocalDate.of(1990,12,31), "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/","mnjkiu89" /**/);
                	
                	IUsr.setURL("isabelita", "isabelita.webp");
                	IUsr.setURL("anibal", "anibal.jpg");
                	IUsr.setURL("lachiqui", "lachiqui.jpg");
                	IUsr.setURL("eldiez", "eldiez.jpg");
                	IUsr.setURL("meche", "meche.jpg");
                	IUsr.setURL("washington", "washington.jpg");
                	IUsr.setURL("mastropiero", "mastropiero.png");
                	IUsr.setURL("chino", "chino.jpg");
                	IUsr.setURL("tony", "tony.jpg");
                	IUsr.setURL("bobesponja", "bobesponja.jpg");
                	IUsr.setURL("eleven11", "eleven11.jpg");
                	IUsr.setURL("elelvis", "elelvis.webp");
                	IUsr.setURL("waston", "waston.jpg");
                	
                	//SEGUIDORES Y SEGUIDOS
                	IUsr.agregarSeguidorYSeguido("lachiqui", "isabelita");
                	IUsr.agregarSeguidorYSeguido("lachiqui", "mastropiero");
                	IUsr.agregarSeguidorYSeguido("lachiqui", "washington");
                	IUsr.agregarSeguidorYSeguido("lachiqui", "eldiez");
                	IUsr.agregarSeguidorYSeguido("lachiqui", "meche");
                	IUsr.agregarSeguidorYSeguido("isabelita", "lachiqui");
                	IUsr.agregarSeguidorYSeguido("anibal", "waston");
                	IUsr.agregarSeguidorYSeguido("anibal", "eleven11");
                	IUsr.agregarSeguidorYSeguido("anibal", "bobesponja");
                	IUsr.agregarSeguidorYSeguido("anibal", "meche");
                	IUsr.agregarSeguidorYSeguido("waston", "isabelita");
                	IUsr.agregarSeguidorYSeguido("waston", "washington");
                	IUsr.agregarSeguidorYSeguido("elelvis", "tony");
                	IUsr.agregarSeguidorYSeguido("elelvis", "eldiez");
                	IUsr.agregarSeguidorYSeguido("elelvis", "bobesponja");
                	IUsr.agregarSeguidorYSeguido("eleven11", "lachiqui");
                	IUsr.agregarSeguidorYSeguido("eleven11", "waston");
                	IUsr.agregarSeguidorYSeguido("eleven11", "mastropiero");
                	IUsr.agregarSeguidorYSeguido("bobesponja", "anibal");
                	IUsr.agregarSeguidorYSeguido("bobesponja", "eleven11");
                	IUsr.agregarSeguidorYSeguido("tony", "chino");
                	IUsr.agregarSeguidorYSeguido("tony", "eldiez");
                	IUsr.agregarSeguidorYSeguido("chino", "elelvis");
                	IUsr.agregarSeguidorYSeguido("chino", "mastropiero");
                	IUsr.agregarSeguidorYSeguido("chino", "washington");
                	IUsr.agregarSeguidorYSeguido("chino", "meche");
                	IUsr.agregarSeguidorYSeguido("washington", "waston");
                	IUsr.agregarSeguidorYSeguido("washington", "mastropiero");
                	IUsr.agregarSeguidorYSeguido("eldiez", "tony");
                	IUsr.agregarSeguidorYSeguido("meche", "lachiqui");
                	IUsr.agregarSeguidorYSeguido("meche", "isabelita");
                	IUsr.agregarSeguidorYSeguido("meche", "waston");
                	IUsr.agregarSeguidorYSeguido("meche", "eleven11");
                	
                	
                	try {								//tur    // dep  //ciudad //descripcion //proveedor//duracion //costo //date 
        				String [] cats= new String[1];
        				cats[0]="";
        				
                		IAT.darAltaActividadTuristica("Degusta","Rocha","Rocha","Festival gastronomico de productos locales en Rocha", "https://www.youtube.com/embed/Y0WDQRo704E", "washington", 3,800 ,LocalDate.of(2022, 7, 20),cats);
                		IAT.agregarCategoriaActividad("Degusta", "Gastronomia");
                		IAT.cambiarEstado(logica.ActividadAT.Estado.Confirmada,"Degusta");
                		
        				IAT.darAltaActividadTuristica("Teatro con Sabores","Rocha","Rocha","En el mes aniversario del Club Deportivo UniÃ³n de Rocha te invitamos a una merienda deliciosa.", "https://www.youtube.com/embed/Lxit3xvKShc", "washington", 3,500 ,LocalDate.of(2022, 7, 21),cats);
        				IAT.agregarCategoriaActividad("Teatro con Sabores", "Gastronomia");
                		IAT.agregarCategoriaActividad("Teatro con Sabores", "Cultura y Patrimonio");
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Confirmada,"Teatro con Sabores");
        				
        				IAT.darAltaActividadTuristica("Tour por Colonia del Sacramento","Colonia","Colonia del Sacramento","Con guÃ­a especializado y en varios idiomas. Varios circuitos posibles.", "https://www.youtube.com/embed/zVDGjURCBz8", "meche",2,400, LocalDate.of(2022, 8, 1),cats);
        				IAT.agregarCategoriaActividad("Tour por Colonia del Sacramento", "Cultura y Patrimonio");
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Confirmada,"Tour por Colonia del Sacramento");
        				
        				IAT.darAltaActividadTuristica("Almuerzo en el Real de San Carlos","Colonia","Colonia del Sacramento","Restaurante en la renovada Plaza de Toros con menÃº internacional", "https://www.youtube.com/embed/wfyDxicM1PQ", "meche",2,800, LocalDate.of(2022, 8, 1),cats);
        				IAT.agregarCategoriaActividad("Almuerzo en el Real de San Carlos", "Gastronomia");
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Confirmada,"Almuerzo en el Real de San Carlos");
        				
        				IAT.darAltaActividadTuristica("Almuerzo en Valle del Lunarejo","Rivera","Tranqueras","Almuerzo en la Posada con ticket fijo. MenÃº que incluye bebida y postre casero.", "https://www.youtube.com/embed/5uaEdiQVEEE", "eldiez",2,300 , LocalDate.of(2022, 8, 1),cats);
        				IAT.agregarCategoriaActividad("Almuerzo en Valle del Lunarejo", "Campo y Naturaleza");
        				IAT.agregarCategoriaActividad("Almuerzo en Valle del Lunarejo", "Gastronomia");
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Confirmada,"Almuerzo en Valle del Lunarejo");
        				
        				IAT.darAltaActividadTuristica("Cabalgata en Valle del Lunarejo","Rivera","Tranqueras","Cabalgata por el Ã¡rea protegida. Varios recorridos para elegir.", "https://www.youtube.com/embed/dlUb22YfXDg", "eldiez",2,150, LocalDate.of(2022, 8, 1),cats);
        				IAT.agregarCategoriaActividad("Cabalgata en Valle del Lunarejo", "Campo y Naturaleza");
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Confirmada,"Cabalgata en Valle del Lunarejo");
        				
        				IAT.darAltaActividadTuristica("Bus turistico Colonia","Colonia","Colonia del Sacramento","Recorrida por los principales atractivos de la ciudad", "https://www.youtube.com/embed/FCFoe4ibkk8", "meche", 3,600 ,LocalDate.of(2022, 9, 1),cats);
        				IAT.agregarCategoriaActividad("Bus turistico Colonia", "Cultura y Patrimonio");				
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Agregada,"Bus turistico Colonia");
        				
        				IAT.darAltaActividadTuristica("Colonia Premium Tour","Colonia","Colonia del Sacramento","Visita lugares exclusivos y relevantes", null, "meche", 4,2600 ,LocalDate.of(2022, 9, 3),cats);
        				IAT.agregarCategoriaActividad("Colonia Premium Tour", "Cultura y Patrimonio");		
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Rechazada,"Colonia Premium Tour");
        				
        				IAT.darAltaActividadTuristica("Deportes nauticos sin uso de motor","Rocha","Rocha","kitsurf - windsurf - kayakismo - canotaje en Rocha", "https://www.youtube.com/embed/a7Lfx4Flb28", "washington", 3,1200 ,LocalDate.of(2022, 9, 5),cats);
        				IAT.agregarCategoriaActividad("Deportes nauticos sin uso de motor", "Aventura y Deporte");	
        				IAT.agregarCategoriaActividad("Deportes nauticos sin uso de motor", "Turismo Playas");	
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Agregada,"Deportes nauticos sin uso de motor");
        				
        				IAT.darAltaActividadTuristica("Descubre Rivera","Rivera","Rivera","Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubicacion geografica privilegiada", null, "eldiez", 2,650 ,LocalDate.of(2022, 9, 16),cats);
        				IAT.agregarCategoriaActividad("Descubre Rivera", "Cultura y Patrimonio");	
        				IAT.cambiarEstado(logica.ActividadAT.Estado.Rechazada,"Descubre Rivera");
        				
        				
        				IAT.setURLActividad("Degusta", "degusta.jpg");
        				IAT.setURLActividad("Teatro con Sabores", "teatroconsabores.jpg");
        				IAT.setURLActividad("Tour por Colonia del Sacramento", "tourporcolonia.jpg");
        				IAT.setURLActividad("Almuerzo en el Real de San Carlos", "almuerzosancarlos.jpg");
        				IAT.setURLActividad("Almuerzo en Valle del Lunarejo", "vallelunarejo.jpg");
        				IAT.setURLActividad("Cabalgata en Valle del Lunarejo", "cabalgatalunarejo.jpeg");
        				IAT.setURLActividad("Bus turistico Colonia", "buscolonia.jpg");
        				IAT.setURLActividad("Colonia Premium Tour", "coloniapremium.jpg");
        				IAT.setURLActividad("Deportes nauticos sin uso de motor", "deportesnauticos.jpg");
        				IAT.setURLActividad("Descubre Rivera", "descubrerivera.jpeg");
        				
        				
        				
        				//ACTIVIDADES FAVORITAS
        				IUsr.agregarActividadFavorita("Degusta", "lachiqui");
        				IUsr.agregarActividadFavorita("Tour por Colonia del Sacramento","lachiqui");
        				IUsr.agregarActividadFavorita("Tour por Colonia del Sacramento","isabelita");
        				IUsr.agregarActividadFavorita("Almuerzo en el Real de San Carlos","isabelita");
        				IUsr.agregarActividadFavorita("Almuerzo en el Real de San Carlos","anibal");
        				IUsr.agregarActividadFavorita("Almuerzo en Valle del Lunarejo","anibal");
        				IUsr.agregarActividadFavorita("Cabalgata en Valle del Lunarejo","anibal");
        				IUsr.agregarActividadFavorita("Degusta","waston");
        				IUsr.agregarActividadFavorita("Teatro con Sabores","waston");
        				IUsr.agregarActividadFavorita("Tour por Colonia del Sacramento","waston");
        				IUsr.agregarActividadFavorita("Almuerzo en el Real de San Carlos","waston");
        				IUsr.agregarActividadFavorita("Cabalgata en Valle del Lunarejo","elelvis");
        				IUsr.agregarActividadFavorita("Degusta","eleven11");
        				IUsr.agregarActividadFavorita("Teatro con Sabores","eleven11");
        				IUsr.agregarActividadFavorita("Tour por Colonia del Sacramento","bobesponja");
        				IUsr.agregarActividadFavorita("Almuerzo en el Real de San Carlos","bobesponja");
        				IUsr.agregarActividadFavorita("Teatro con Sabores","tony");
        				

                	} catch (ExisteActividadException | NoExisteDepartamentoException | NoExisteProveedorException e) {
        				e.printStackTrace();
        			}

                	//DATOS PAQUETE
                	try {
                		IPaq.darAltaPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomÃ­a", 20, 60,LocalDate.of(2022,8,10));
                		IPaq.darAltaPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 15, 45,LocalDate.of(2022,8,1));
                		IPaq.darAltaPaquete("Valle Del Lunarejo", "Visite un ´area protegida con un paisaje natural hermoso", 15, 60,LocalDate.of(2022,9,15));
                		IPaq.darAltaPaquete("Rocha De Fiesta", "Para cerrar el año a lo grande en nuestro departamento mas oceanico", 30, 45,LocalDate.of(2022,11,07));
                	} catch (ExistePaqueteException e) {
                		e.printStackTrace();
                	}
                	
                	IPaq.setURLPaquete("Disfrutar Rocha", "disfrutarRocha.jpg");
                	IPaq.setURLPaquete("Un día en Colonia", "undiaencolonia.jpg");
                	IPaq.setURLPaquete("Valle Del Lunarejo", "valledellunarejo.jpg");
                	IPaq.setURLPaquete("Rocha De Fiesta", "vacioAct.webp");
                	
                	IPaq.ingresarActividadAlPaquete("Degusta", "Disfrutar Rocha");
                	IPaq.ingresarActividadAlPaquete("Teatro con Sabores", "Disfrutar Rocha");
                	IPaq.ingresarActividadAlPaquete("Tour por Colonia del Sacramento", "Un día en Colonia");
                	IPaq.ingresarActividadAlPaquete("Almuerzo en Valle del Lunarejo", "Valle Del Lunarejo");
                	IPaq.ingresarActividadAlPaquete("Cabalgata en Valle del Lunarejo", "Valle Del Lunarejo");
                	IPaq.ingresarActividadAlPaquete("Degusta", "Rocha De Fiesta");
                	
                	
                	//salidas
                	IAT.darAltaSalidacarga("Degusta", "Degusta Agosto",LocalDate.of(2022,8,20), LocalTime.of(17, 0), 20,"Sociedad Agropecuaria de Rocha",LocalDate.of(2022,7, 21));
                	IAT.darAltaSalidacarga("Degusta", "Degusta Setiembre", LocalDate.of(2022,9,3),LocalTime.of(17, 0), 20,"Sociedad Agropecuaria de Rocha",LocalDate.of(2022,7, 22));
                	IAT.darAltaSalidacarga("Teatro con Sabores", "Teatro con Sabores 1", LocalDate.of(2022,9,4),LocalTime.of(18, 0), 30,"Club Deportivo UniÃ³n",LocalDate.of(2022,7, 23));
                	IAT.darAltaSalidacarga("Teatro con Sabores", "Teatro con Sabores 2", LocalDate.of(2022,9,11),LocalTime.of(18, 0), 30,"Club Deportivo UniÃ³n",LocalDate.of(2022,7, 23));
                	IAT.darAltaSalidacarga("Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 11-09", LocalDate.of(2022,9,11),LocalTime.of(10, 0), 5,"Encuentro en la base del Faro",LocalDate.of(2022,8, 5));
                	IAT.darAltaSalidacarga("Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 18-09", LocalDate.of(2022,9,18),LocalTime.of(10, 0), 5,"Encuentro en la base del Faro",LocalDate.of(2022,8, 5));
                	IAT.darAltaSalidacarga("Almuerzo en el Real de San Carlos", "Almuerzo 1", LocalDate.of(2022,9,18),LocalTime.of(12, 0), 5,"Restaurante de la Plaza de Toros",LocalDate.of(2022,8, 4));
                	IAT.darAltaSalidacarga("Almuerzo en el Real de San Carlos", "Almuerzo 2", LocalDate.of(2022,9,25),LocalTime.of(12, 0), 5,"Restaurante de la Plaza de Toros",LocalDate.of(2022,8, 4));
                	IAT.darAltaSalidacarga("Almuerzo en Valle del Lunarejo", "Almuerzo 3", LocalDate.of(2022,9,10),LocalTime.of(12, 0), 4,"Posada Del Lunarejo",LocalDate.of(2022,8, 15));
                	IAT.darAltaSalidacarga("Almuerzo en Valle del Lunarejo", "Almuerzo 4", LocalDate.of(2022,9,11),LocalTime.of(12, 0), 4,"Posada Del Lunarejo",LocalDate.of(2022,8, 15));
                	IAT.darAltaSalidacarga("Cabalgata en Valle del Lunarejo", "Cabalgata 1", LocalDate.of(2022,9,10),LocalTime.of(16, 0), 4,"Posada Del Lunarejo",LocalDate.of(2022,8, 15));
                	IAT.darAltaSalidacarga("Cabalgata en Valle del Lunarejo", "Cabalgata 2", LocalDate.of(2022,9,11),LocalTime.of(16, 0), 4,"Posada Del Lunarejo",LocalDate.of(2022,8, 15));
                	IAT.darAltaSalidacarga("Degusta", "Degusta Octubre", LocalDate.of(2022,10,30),LocalTime.of(17, 0), 20,"Sociedad Agropecuaria de Rocha",LocalDate.of(2022,9, 22));
                	IAT.darAltaSalidacarga("Degusta", "Degusta Noviembre", LocalDate.of(2022,11,5),LocalTime.of(17, 0), 20,"Sociedad Agropecuaria de Rocha",LocalDate.of(2022,10, 2));
                	IAT.darAltaSalidacarga("Teatro con Sabores", "Teatro con Sabores 3", LocalDate.of(2022,11,11),LocalTime.of(18, 0), 30,"Club Deportivo Union",LocalDate.of(2022,8, 25));
                	IAT.darAltaSalidacarga("Tour por Colonia del Sacramento", "Tour Colonia del Sacramento 30-10", LocalDate.of(2022,10,30),LocalTime.of(10, 0), 10,"Encuentro en la base del Faro",LocalDate.of(2022,9, 7));
                	IAT.darAltaSalidacarga("Cabalgata en Valle del Lunarejo", "Cabalgata Extrema", LocalDate.of(2022,10,30),LocalTime.of(16, 0), 4,"Posada Del Lunarejo",LocalDate.of(2022,9, 15));
                	IAT.darAltaSalidacarga("Almuerzo en el Real de San Carlos", "Almuerzo en el Real 1", LocalDate.of(2022,10,30),LocalTime.of(12, 0), 10,"Restaurante de la Plaza de Toros",LocalDate.of(2022,10, 10));
                	IAT.darAltaSalidacarga("Degusta", "Degusta Diciembre", LocalDate.of(2022,12,02), LocalTime.of(17, 0), 20, "Sociedad Agropecuaria de Rocha", LocalDate.of(2022, 07, 11));
                	IAT.darAltaSalidacarga("Teatro con Sabores", "Teatro con Sabores 4", LocalDate.of(2022,12,03),LocalTime.of(18, 0), 30,"Club Deportivo Union",LocalDate.of(2022, 11, 07));
                	
                	IAT.setURLSalida("Degusta Agosto", "Degusta", "degustaagosto.jpg");
                	IAT.setURLSalida("Degusta Setiembre", "Degusta", "degustasetiembre.jpg");
                	IAT.setURLSalida("Teatro con Sabores 1", "Teatro con Sabores", "teatrosabores.jpg");
                	IAT.setURLSalida("Teatro con Sabores 2", "Teatro con Sabores", "teatrosabores2.jpg");
                	IAT.setURLSalida("Tour Colonia del Sacramento 11-09", "Tour por Colonia del Sacramento", "tourcolonia1.jpg");
                	IAT.setURLSalida("Tour Colonia del Sacramento 18-09", "Tour por Colonia del Sacramento", "tourcolonia2.jpg");
                	IAT.setURLSalida("Almuerzo 1", "Almuerzo en el Real de San Carlos", "vacioAct.webp");
                	IAT.setURLSalida("Almuerzo 2", "Almuerzo en el Real de San Carlos", "vacioAct.webp");
                	IAT.setURLSalida("Almuerzo 3", "Almuerzo en Valle del Lunarejo", "vacioAct.webp");
                	IAT.setURLSalida("Almuerzo 4", "Almuerzo en Valle del Lunarejo", "vacioAct.webp");
                	IAT.setURLSalida("Cabalgata 1", "Cabalgata en Valle del Lunarejo", "cabalgata1.jpg");
                	IAT.setURLSalida("Cabalgata 2", "Cabalgata en Valle del Lunarejo", "vacioAct.webp");
                	IAT.setURLSalida("Degusta Octubre", "Degusta", "degustaoctubre.webp");
                	IAT.setURLSalida("Degusta Noviembre", "Degusta", "degustanoviembre.jpg");
                	IAT.setURLSalida("Teatro con Sabores 3", "Teatro con Sabores", "vacioAct.webp");
                	IAT.setURLSalida("Tour Colonia del Sacramento 30-10", "Tour por Colonia del Sacramento", "tourcolonia3.jpg");
                	IAT.setURLSalida("Cabalgata Extrema", "Cabalgata en Valle del Lunarejo", "cabalgataextrema.jpeg");
                	IAT.setURLSalida("Almuerzo en el Real 1", "Almuerzo en el Real de San Carlos", "vacioAct.webp");
                	IAT.setURLSalida("Teatro con Sabores 4", "Teatro con Sabores", "vacioAct.webp");
                	IAT.setURLSalida("Degusta Diciembre", "Degusta", "teatro4.jpg");
                	
                	//DATOS INSCRIPCIONES
                	try {
                		IAT.inscribirASalida("lachiqui", "Degusta","Degusta Agosto", 3, LocalDate.of(2022,8,15));
                		IAT.inscribirASalida("elelvis", "Degusta","Degusta Agosto", 5, LocalDate.of(2022,8,16));
                		IAT.inscribirASalida("lachiqui", "Tour por Colonia del Sacramento","Tour Colonia del Sacramento 18-09", 3, LocalDate.of(2022,8,18));
                		IAT.inscribirASalida("isabelita", "Tour por Colonia del Sacramento","Tour Colonia del Sacramento 18-09", 1, LocalDate.of(2022,8,19));
                		IAT.inscribirASalida("mastropiero", "Almuerzo en el Real de San Carlos","Almuerzo 2", 2, LocalDate.of(2022,8,19));
                		IAT.inscribirASalida("chino", "Teatro con Sabores","Teatro con Sabores 1", 1, LocalDate.of(2022,8,19));
                		IAT.inscribirASalida("chino", "Teatro con Sabores","Teatro con Sabores 2", 10, LocalDate.of(2022,8,20));
                		IAT.inscribirASalida("bobesponja", "Teatro con Sabores","Teatro con Sabores 2", 2, LocalDate.of(2022,8,20));
                		IAT.inscribirASalida("anibal", "Teatro con Sabores","Teatro con Sabores 2", 1, LocalDate.of(2022,8,21));
                		IAT.inscribirASalida("tony", "Degusta","Degusta Setiembre", 11, LocalDate.of(2022,8,21));
                		IAT.inscribirASalida("lachiqui", "Degusta","Degusta Noviembre", 2, LocalDate.of(2022,10,3));
                		IAT.inscribirASalida("lachiqui", "Teatro con Sabores","Teatro con Sabores 3", 2, LocalDate.of(2022,10,3));
                		IAT.inscribirASalida("elelvis", "Degusta","Degusta Setiembre", 5, LocalDate.of(2022,9,2));
                		IAT.inscribirASalida("elelvis", "Teatro con Sabores","Teatro con Sabores 1", 5, LocalDate.of(2022,9,2));
                		IAT.inscribirASalida("lachiqui", "Tour por Colonia del Sacramento","Tour Colonia del Sacramento 11-09", 5, LocalDate.of(2022,9,3));
                		IAT.inscribirASalida("lachiqui", "Almuerzo en el Real de San Carlos","Almuerzo 1", 5, LocalDate.of(2022,9,3));
                		IAT.inscribirASalida("waston", "Tour por Colonia del Sacramento","Tour Colonia del Sacramento 18-09", 1, LocalDate.of(2022,9,5));
                		IAT.inscribirASalida("waston", "Almuerzo en el Real de San Carlos","Almuerzo 2", 1, LocalDate.of(2022,9,5));
                		IAT.inscribirASalida("elelvis", "Tour por Colonia del Sacramento","Tour Colonia del Sacramento 30-10", 2, LocalDate.of(2022,10,2));
                		IAT.inscribirASalida("elelvis", "Almuerzo en el Real de San Carlos","Almuerzo en el Real 1", 2, LocalDate.of(2022,10,11));
                		IAT.inscribirASalida("mastropiero", "Tour por Colonia del Sacramento","Tour Colonia del Sacramento 30-10", 4, LocalDate.of(2022,10,12));
                		IAT.inscribirASalida("mastropiero", "Almuerzo en el Real de San Carlos","Almuerzo en el Real 1", 4, LocalDate.of(2022,10,12));
                	} catch (ExcedeCapacidadException | YaTieneInscripcionAEstaSalidaException e) {
                		e.printStackTrace();
                	}
        			
        			
            	}
            	JOptionPane.showMessageDialog(menuBar, "Se han cargado correctamente los datos", "Cargar Datos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuSistema.add(menuCargarDatos);
        
        
        JMenu menuPaquetes = new JMenu("Paquetes");
        menuBar.add(menuPaquetes);
        
        JMenu menuActividades = new JMenu("Activiades");
        menuBar.add(menuActividades);
        
        JMenuItem menuItemInscripcion = new JMenuItem("Inscripcion a Salida Turistica");
        menuItemInscripcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                // Muestro el InternalFrame para registrar un Alta
            	if(incSalTurInternalFrame.cargarDepartamentos()) {
//            		closeInternalFrames();
//            		incSalTurInternalFrame.toFront();
            		incSalTurInternalFrame.setVisible(true);            	            		
            	}
            		
            }
        });
        menuActividades.add(menuItemInscripcion);
        
        
        
        JMenuItem menuItemCrearPaquete = new JMenuItem("Crear Paquete de Actividades Turisticas");
        menuItemCrearPaquete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		closeInternalFrames();
        		crerPaqueteInternalFrame.toFront();
        		crerPaqueteInternalFrame.setVisible(true);
        	}
        });
        menuPaquetes.add(menuItemCrearPaquete);
        
        JMenuItem menuItemAltaActividadTuristica = new JMenuItem("Alta Actividad Turistica");
        menuItemAltaActividadTuristica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                // Muestro el InternalFrame para registrar un Alta
//            	closeInternalFrames();
            	if(altAcInternalFrame.cargarDepartamentos()&&altAcInternalFrame.cargarProveedores() && altAcInternalFrame.cargarCategorias())
            		altAcInternalFrame.setVisible(true);

            }
        });
        menuActividades.add(menuItemAltaActividadTuristica);
        
        JMenuItem menuItemConsultaDeSalida = new JMenuItem("Consulta de Salida Turistica");
        menuItemConsultaDeSalida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (ConsSalidaInternalFrame.cargarDepartamentos()) {
            		ConsSalidaInternalFrame.setVisible(true);            		
            	}
            }
        });
        
        menuActividades.add(menuItemConsultaDeSalida);
        
        JMenu mnNewMenu = new JMenu("Usuario");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Alta Usuario");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		altUsrInternalFrame.setVisible(true);
        	}
        });
        mnNewMenu.add(mntmNewMenuItem);
        
        JMenuItem menuItemModificarDatosDeUsuario = new JMenuItem("Modificar Datos De Usuario");
        menuItemModificarDatosDeUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (ModUsrInternalFrame.cargarUsuarios()) {
            		ModUsrInternalFrame.setVisible(true);            		
            	}
            }
        });
        mnNewMenu.add(menuItemModificarDatosDeUsuario);
        

        JMenuItem menuItemConsultaUsuario = new JMenuItem("Consulta De Usuario");
        menuItemConsultaUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (ConsUsrInternalFrame.cargarUsuarios()) {
            		ConsUsrInternalFrame.setVisible(true);            		
            	}
            }
        });
        mnNewMenu.add(menuItemConsultaUsuario);
        


        JMenuItem menuItemAltaSalidaTuristica = new JMenuItem("Alta de Salida Turistica");
        menuItemAltaSalidaTuristica.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (AltaSalidaInternalFrame.cargarDepartamentos()) {
        			AltaSalidaInternalFrame.setVisible(true);        			
        		}
        	}
        });
        menuActividades.add(menuItemAltaSalidaTuristica);

        JMenuItem menuItemConsultaAT = new JMenuItem("Consulta Actividad Turistica");
        menuItemConsultaAT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ConsultaATInternalFrame.cargarDepartamentos()) {
        			ConsultaATInternalFrame.setVisible(true);        			
        		}
        		
        	}
        });
        menuActividades.add(menuItemConsultaAT);
        

        JMenuItem menuItemAgregarAct = new JMenuItem("Agregar Actividad A Paquete");
        menuItemAgregarAct.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(agrActPaqInternalFrame.cargarDepartamentos() && agrActPaqInternalFrame.cargarPaquetes())
        			agrActPaqInternalFrame.setVisible(true);
        	}
        });
        menuPaquetes.add(menuItemAgregarAct);

        JMenuItem menuItemConsultaPaqAT = new JMenuItem("Consulta de Paquete de Actividad Turistica");
        menuItemConsultaPaqAT.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (ConsultaPaqATInternalFrame.cargarPaquetes()) {
        			ConsultaPaqATInternalFrame.setVisible(true);        			
        		}
        	}
        });
        menuPaquetes.add(menuItemConsultaPaqAT);

        JMenuItem menuItemAltaCategoria = new JMenuItem("Alta Categoria");
        menuItemAltaCategoria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AltaCatInternalFrame.setVisible(true);
        	}
        });
        menuActividades.add(menuItemAltaCategoria);
        
        JMenuItem menuItemAcepRech = new JMenuItem("Aceptar/Rechazar Actividad");
        menuItemAcepRech.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(acepRechActInternalFrame.cargarActividades())
        			acepRechActInternalFrame.setVisible(true);
        	}
        });
        menuActividades.add(menuItemAcepRech);
        
	}


}
