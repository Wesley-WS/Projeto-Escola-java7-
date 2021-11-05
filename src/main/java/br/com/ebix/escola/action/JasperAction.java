package br.com.ebix.escola.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.lowagie.text.pdf.codec.Base64.OutputStream;
import com.opensymphony.xwork2.ActionSupport;

import br.com.ebix.escola.dao.AlunoDao;
import br.com.ebix.escola.dao.AlunoDaoImpl;
import br.com.ebix.escola.model.Aluno;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperAction extends ActionSupport{
	private static final long serialVersionUID = 8704084135816754792L;
	private List<Aluno> myList;
	private String location = "codAluno.jasper";
	private ByteArrayInputStream pdfStream;
	
	private AlunoDao alunoDao = new AlunoDaoImpl();
	
    public String execute() throws Exception {
    	
    	ServletContext context = ServletActionContext.getServletContext();
    	
        // Create some imaginary persons.
    	Aluno p1 = new Aluno();
    	Aluno p2 = new Aluno();
    	
    	p1.setCod_aluno(5l);
    	p1.setCpf("ok");
    	p1.setDataNascimento(Calendar.getInstance());
    	p1.setEmail("ok");
    	p1.setNome("AHA");
    	p1.setTelefoneCelular("121212121");
    	p1.setTelefoneResidencial("2323232");
    	
    	p2.setCod_aluno(52l);
    	p2.setCpf("ok2");
    	p2.setDataNascimento(Calendar.getInstance());
    	p2.setEmail("ok2");
    	p2.setNome("AHA2");
    	p2.setTelefoneCelular("121212121");
    	p2.setTelefoneResidencial("2323232");
        
    	// Store people in our dataSource list (normally they would come from a database).
        myList = new ArrayList<Aluno>();
        myList.add(p1);
        myList.add(p2);
        
        try {
        	Map<String, Object> map = new HashMap<String, Object>();

        	JasperReport teste = JasperCompileManager.compileReport(context.getRealPath("/WEB-INF/jasper/alunosRelatorio.jrxml"));
        	JasperPrint print = JasperFillManager.fillReport(teste, map, new JRBeanCollectionDataSource(myList, false));
        	
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, baos);
			setPdfStream(new ByteArrayInputStream(baos.toByteArray()));
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

	public ByteArrayInputStream getPdfStream() {
		return pdfStream;
	}

	public void setPdfStream(ByteArrayInputStream pdfStream) {
		this.pdfStream = pdfStream;
	}
	
}
