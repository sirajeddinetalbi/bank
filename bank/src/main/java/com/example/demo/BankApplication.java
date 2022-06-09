package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.EmployesRepository;
import com.example.demo.dao.GroupesRepository;
import com.example.demo.dao.OperationRepository;
import com.example.demo.model.Client;
import com.example.demo.model.CompteCC;
import com.example.demo.model.CompteEP;
import com.example.demo.model.Employes;
import com.example.demo.model.Groupes;
import com.example.demo.model.Retrait;
import com.example.demo.service.IBankImplementation;

@SpringBootApplication
public class BankApplication implements ApplicationRunner{

    @Autowired
    private ClientRepository CR;
    
    @Autowired
    private CompteRepository CtR;
    
    @Autowired
    private EmployesRepository ER;
    
    @Autowired
    private GroupesRepository GR;
    
    @Autowired
    private OperationRepository OR;
    
    @Autowired
    private IBankImplementation BM;
    
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		/*Client c1=new Client(null, "firas","firas@gmail.com", null);
		Client c2=new Client(null, "fatma","fatma@gmail.com", null);
		Client c3=new Client(null, "hedi","hedi@gmail.com", null);
		Client c4=new Client(null, "radhia","radhia@gmail.com", null);
		CR.saveAll(Arrays.asList(c1,c2,c3,c4));
		
		Employes e1= new Employes(null, "maher", null, null, null);
		Employes e2= new Employes(null, "majd", null, null, null);
		Employes e3= new Employes(null, "hassen", null, null , null);
		Employes e4= new Employes(null, "saif", null, null, null);
		ER.saveAll(Arrays.asList(e1,e2,e3,e4));
		
		Groupes g1= new Groupes(null, "caisse", null);
		Groupes g2= new Groupes(null, "acceuil", null);
		GR.saveAll(Arrays.asList(g1,g2));
		
		Collection<Groupes> col = new ArrayList<Groupes>();
		col.add(g1);
		col.add(g2);

		Employes e=new Employes(null,"mohamed","mohamed@gmail.com", e1 ,col);
		ER.save(e);
		
		CompteCC co1 =new CompteCC("c1", new Date(), 0, c1, e1, null, 0);
		CompteEP co2 =new CompteEP("E1", new Date(), 0, c2, e2, null, 0);
		CtR.save(co1);
		CtR.save(co2);
		
		Retrait o1 =new Retrait(null,new Date(), 0, co1, e1);
		Versement o2 =new Versement(null,new Date(), 0, co2, e2);
		OR.save(o1);
		OR.save(o2);
		
		BM.verser("c1",10000);*/





	}

}
