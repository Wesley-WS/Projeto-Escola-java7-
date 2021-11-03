package br.com.ebix.escola.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import br.com.ebix.escola.model.Aluno;
import net.sf.jasperreports.engine.JasperCompileManager;

public class JasperAction extends ActionSupport{
	private static final long serialVersionUID = 8704084135816754792L;
	private List<Aluno> myList;
	private String location = "codAluno.jasper";
	
    public String execute() throws Exception {
    	
    	ServletContext context = ServletActionContext.getServletContext();
    	
        // Create some imaginary persons.
    	Aluno p1 = new Aluno();
    	Aluno p2 = new Aluno();
    	Aluno p3 = new Aluno();
    	Aluno p4 = new Aluno();
    	
    	p1.setCod_aluno((long) 51);
    	p2.setCod_aluno((long) 52);
    	p3.setCod_aluno((long) 53);
    	p4.setCod_aluno((long) 54);
    	
        // Store people in our dataSource list (normally they would come from a database).
        myList = new ArrayList<Aluno>();
        myList.add(p1);
        myList.add(p2);
        myList.add(p3);
        myList.add(p4);
        /*
        String jrxmlPath = "alunoescola.jrxml";
        String jasperPath = context.getRealPath("") + File.separator + location;
        System.out.println(context.getRealPath(""));
        System.out.println(getClass().getResource( "/jasper/alunoescola.jrxml" ));
        // Normally we would provide a pre-compiled .jrxml file
        // or check to make sure we don't compile on every request.*/
        try {
        	JasperCompileManager.compileReportToFile(
        			context.getRealPath("/WEB-INF/jasper/codAluno.jrxml"),
        			context.getRealPath("/WEB-INF/jasper/codAluno.jasper"));
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public List<Aluno> getMyList() {
        return myList;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    /*
    URL arquivo = getClass().getResource( "/relatorios/Relatorio_sem_nome_2.jasper" );	    
    JasperReport jasperReport = (JasperReport) JRLoader.loadObject( arquivo );
    
    Map map = null;
    Connection con = null;	    
    
    // Aqui eu estou passando um jasperReport. Mas eu testei com um inputStream como Cristiano.Zanata disse e funcionada tbm. :)
    JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport, map, new JRDataSource() {

	private int max = 3;
	private int cont = 0;
	private String valor;

	public Object getFieldValue(JRField jrField) throws JRException {

	    return "main";
	}

	public boolean next() throws JRException {
	    // TODO Auto-generated method stub
	    if (cont++ >= max) {
		System.out.println("false");
		return false;
	    } else {
		System.out.println("true");
		return true;
	    }
	}
    });
    
    JasperViewer jasperViewer = new JasperViewer( jasperPrint, false );
    jasperViewer.setVisible( true );
   
    } catch (JRException ex) {
	Logger.getLogger(TentandoFazerUmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
    }*/
}
