package structurer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import resources.LocateResource;

public class ElementCreator {
	
	private static final String PROGFILE = "ifs_programs.csv";
//	D:\DVANDECA\Desktop\My Documents\My LabsWork\GitRepositories\Clustering\FindClusters\src\resources\ifs_programs.csv
	
	private boolean fullModel;
	
	public ElementCreator(boolean fullModel) {
		super();	
		this.fullModel = fullModel;
	}
	
	
	
	public  void createBaseElements ( ArrayList<Table> tables,  ArrayList<Program> programs,
	ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
	{	

		if (this.fullModel) {
			createBaseElementsTables (tables); 
			createBaseElementsPrograms( programs);
			createBaseElementsTargetIFSModules( ifsModules);
			createBaseElementsTargetLBBModules( lbbModules);		
		} else
		{
			DefineUnitTestData (tables, programs, ifsModules, lbbModules);
		}
	}

	private  void createBaseElementsTargetLBBModules
	(  ArrayList<TargetModule> lbbModules)
	{
	TargetLBBModule Current_Account_Engine = new TargetLBBModule ("Current_Account_Engine");	lbbModules.add (Current_Account_Engine);
	TargetLBBModule Customer_Billing = new TargetLBBModule ("Customer_Billing");	lbbModules.add (Customer_Billing);
	TargetLBBModule Customer_Reporting = new TargetLBBModule ("Customer_Reporting");	lbbModules.add (Customer_Reporting);
	TargetLBBModule Reconciliation_Engine = new TargetLBBModule ("Reconciliation_Engine");	lbbModules.add (Reconciliation_Engine);
	TargetLBBModule Party_Management = new TargetLBBModule ("Party_Management");	lbbModules.add (Party_Management);
	TargetLBBModule Cash_Management_Engine = new TargetLBBModule ("Cash_Management_Engine");	lbbModules.add (Cash_Management_Engine);
	TargetLBBModule Clearing_and_Settlement_Engine = new TargetLBBModule ("Clearing_and_Settlement_Engine");	lbbModules.add (Clearing_and_Settlement_Engine);
	TargetLBBModule Instruction_and_Order_Management = new TargetLBBModule ("Instruction_and_Order_Management");	lbbModules.add (Instruction_and_Order_Management);
	TargetLBBModule Payments_Execution_Engine = new TargetLBBModule ("Payments_Execution_Engine");	lbbModules.add (Payments_Execution_Engine);
	TargetLBBModule Financial_Markets_Trade_engine = new TargetLBBModule ("Financial_Markets_Trade_engine");	lbbModules.add (Financial_Markets_Trade_engine);
	TargetLBBModule Securities_Market_Order_Mngt = new TargetLBBModule ("Securities_Market_Order_Mngt");	lbbModules.add (Securities_Market_Order_Mngt);
	TargetLBBModule Accounting_Rule_Engine = new TargetLBBModule ("Accounting_Rule_Engine");	lbbModules.add (Accounting_Rule_Engine);
	TargetLBBModule General_Ledger = new TargetLBBModule ("General_Ledger");	lbbModules.add (General_Ledger);
	TargetLBBModule Local_GAAP_Reporting_Layer = new TargetLBBModule ("Local_GAAP_Reporting_Layer");	lbbModules.add (Local_GAAP_Reporting_Layer);
	TargetLBBModule Local_Regulatory_Reporting_Layer = new TargetLBBModule ("Local_Regulatory_Reporting_Layer");	lbbModules.add (Local_Regulatory_Reporting_Layer);
	TargetLBBModule Local_Reporting_Layer = new TargetLBBModule ("Local_Reporting_Layer");	lbbModules.add (Local_Reporting_Layer);
	TargetLBBModule Local_Tax_Reporting_Layer = new TargetLBBModule ("Local_Tax_Reporting_Layer");	lbbModules.add (Local_Tax_Reporting_Layer);
	TargetLBBModule Product_Ledger = new TargetLBBModule ("Product_Ledger");	lbbModules.add (Product_Ledger);
	TargetLBBModule Business_Lending_Engine_ = new TargetLBBModule ("Business_Lending_Engine_");	lbbModules.add (Business_Lending_Engine_);
	TargetLBBModule Collateral_management = new TargetLBBModule ("Collateral_management");	lbbModules.add (Collateral_management);
	TargetLBBModule Consumer_Loans_Engine = new TargetLBBModule ("Consumer_Loans_Engine");	lbbModules.add (Consumer_Loans_Engine);
	TargetLBBModule Corporate_and_FI_Lending_Engine = new TargetLBBModule ("Corporate_and_FI_Lending_Engine");	lbbModules.add (Corporate_and_FI_Lending_Engine);
	TargetLBBModule Mortgages_Engine = new TargetLBBModule ("Mortgages_Engine");	lbbModules.add (Mortgages_Engine);
	TargetLBBModule Savings_Engine = new TargetLBBModule ("Savings_Engine");	lbbModules.add (Savings_Engine);
	TargetLBBModule Trade_Finance_Engine = new TargetLBBModule ("Trade_Finance_Engine");	lbbModules.add (Trade_Finance_Engine);
	TargetLBBModule Arrangement_Management = new TargetLBBModule ("Arrangement_Management");	lbbModules.add (Arrangement_Management);
	TargetLBBModule Reference_Data_Manager = new TargetLBBModule ("Reference_Data_Manager");	lbbModules.add (Reference_Data_Manager);
	}
	
	private  void createBaseElementsTargetIFSModules
	(  ArrayList<TargetModule> ifsModules)
	{
		TargetIFSModule General_Ledger = new TargetIFSModule ("General_Ledger");	ifsModules.add (General_Ledger);
		TargetIFSModule Account_Management = new TargetIFSModule ("Account_Management");	ifsModules.add (Account_Management);
		TargetIFSModule Lending = new TargetIFSModule ("Lending");	ifsModules.add (Lending);
		TargetIFSModule Financial_Markets = new TargetIFSModule ("Financial_Markets");	ifsModules.add (Financial_Markets);
		TargetIFSModule Cash_Management = new TargetIFSModule ("Cash_Management");	ifsModules.add (Cash_Management);
		TargetIFSModule CORE = new TargetIFSModule ("CORE");	ifsModules.add (CORE);
		TargetIFSModule auxiliary = new TargetIFSModule ("auxiliary");	ifsModules.add (auxiliary);
		
	}
	
	private void createBaseElementsTables( ArrayList<Table> tables) {
		
		Table D_AADVO = new Table ("AADVO");	tables.add (D_AADVO);
		Table D_AAFRL = new Table ("AAFRL");	tables.add (D_AAFRL);
		Table D_AAFRO = new Table ("AAFRO");	tables.add (D_AAFRO);
		Table D_AAISO = new Table ("AAISO");	tables.add (D_AAISO);
		Table D_AASPL = new Table ("AASPL");	tables.add (D_AASPL);
		Table D_AASPS = new Table ("AASPS");	tables.add (D_AASPS);
		Table D_ABA1L = new Table ("ABA1L");	tables.add (D_ABA1L);
		Table D_ABA1S = new Table ("ABA1S");	tables.add (D_ABA1S);
		Table D_ABASL = new Table ("ABASL");	tables.add (D_ABASL);
		Table D_ABASS = new Table ("ABASS");	tables.add (D_ABASS);
		Table D_ABCLL = new Table ("ABCLL");	tables.add (D_ABCLL);
		Table D_ABCLO = new Table ("ABCLO");	tables.add (D_ABCLO);
		Table D_ABKTL = new Table ("ABKTL");	tables.add (D_ABKTL);
		Table D_ABKTS = new Table ("ABKTS");	tables.add (D_ABKTS);
		Table D_ABUDL = new Table ("ABUDL");	tables.add (D_ABUDL);
		Table D_ABUDS = new Table ("ABUDS");	tables.add (D_ABUDS);
		Table D_ACLOG = new Table ("ACLOG");	tables.add (D_ACLOG);
		Table D_ACVRO = new Table ("ACVRO");	tables.add (D_ACVRO);
		Table D_ADEIO = new Table ("ADEIO");	tables.add (D_ADEIO);
		Table D_ADETL = new Table ("ADETL");	tables.add (D_ADETL);
		Table D_ADETS = new Table ("ADETS");	tables.add (D_ADETS);
		Table D_ADLGL = new Table ("ADLGL");	tables.add (D_ADLGL);
		Table D_ADLGS = new Table ("ADLGS");	tables.add (D_ADLGS);
		Table D_ADSNL = new Table ("ADSNL");	tables.add (D_ADSNL);
		Table D_ADSNS = new Table ("ADSNS");	tables.add (D_ADSNS);
		Table D_AEVTO = new Table ("AEVTO");	tables.add (D_AEVTO);
		Table D_AEXRL = new Table ("AEXRL");	tables.add (D_AEXRL);
		Table D_AEXRS = new Table ("AEXRS");	tables.add (D_AEXRS);
		Table D_AFOLO = new Table ("AFOLO");	tables.add (D_AFOLO);
		Table D_AGEXL = new Table ("AGEXL");	tables.add (D_AGEXL);
		Table D_AGEXS = new Table ("AGEXS");	tables.add (D_AGEXS);
		Table D_AHLBO = new Table ("AHLBO");	tables.add (D_AHLBO);
		Table D_AHVBO = new Table ("AHVBO");	tables.add (D_AHVBO);
		Table D_AIBRL = new Table ("AIBRL");	tables.add (D_AIBRL);
		Table D_AIBRO = new Table ("AIBRO");	tables.add (D_AIBRO);
		Table D_AINIO = new Table ("AINIO");	tables.add (D_AINIO);
		Table D_AINTL = new Table ("AINTL");	tables.add (D_AINTL);
		Table D_AINTS = new Table ("AINTS");	tables.add (D_AINTS);
		Table D_AISTO = new Table ("AISTO");	tables.add (D_AISTO);
		Table D_AJSTL = new Table ("AJSTL");	tables.add (D_AJSTL);
		Table D_AJSTO = new Table ("AJSTO");	tables.add (D_AJSTO);
		Table D_ALGRL = new Table ("ALGRL");	tables.add (D_ALGRL);
		Table D_ALGRS = new Table ("ALGRS");	tables.add (D_ALGRS);
		Table D_AMDCO = new Table ("AMDCO");	tables.add (D_AMDCO);
		Table D_AMVTO = new Table ("AMVTO");	tables.add (D_AMVTO);
		Table D_ANEUL = new Table ("ANEUL");	tables.add (D_ANEUL);
		Table D_ANEUO = new Table ("ANEUO");	tables.add (D_ANEUO);
		Table D_ANTUL = new Table ("ANTUL");	tables.add (D_ANTUL);
		Table D_ANTUS = new Table ("ANTUS");	tables.add (D_ANTUS);
		Table D_APPNL = new Table ("APPNL");	tables.add (D_APPNL);
		Table D_APPNO = new Table ("APPNO");	tables.add (D_APPNO);
		Table D_ARBRL = new Table ("ARBRL");	tables.add (D_ARBRL);
		Table D_ARBRO = new Table ("ARBRO");	tables.add (D_ARBRO);
		Table D_ARCDL = new Table ("ARCDL");	tables.add (D_ARCDL);
		Table D_ARCDO = new Table ("ARCDO");	tables.add (D_ARCDO);
		Table D_AREIO = new Table ("AREIO");	tables.add (D_AREIO);
		Table D_ARESL = new Table ("ARESL");	tables.add (D_ARESL);
		Table D_ARESS = new Table ("ARESS");	tables.add (D_ARESS);
		Table D_ARETL = new Table ("ARETL");	tables.add (D_ARETL);
		Table D_ARETO = new Table ("ARETO");	tables.add (D_ARETO);
		Table D_AROTL = new Table ("AROTL");	tables.add (D_AROTL);
		Table D_AROTO = new Table ("AROTO");	tables.add (D_AROTO);
		Table D_ARPPO = new Table ("ARPPO");	tables.add (D_ARPPO);
		Table D_ASELL = new Table ("ASELL");	tables.add (D_ASELL);
		Table D_ASELO = new Table ("ASELO");	tables.add (D_ASELO);
		Table D_ASPAL = new Table ("ASPAL");	tables.add (D_ASPAL);
		Table D_ASPAS = new Table ("ASPAS");	tables.add (D_ASPAS);
		Table D_ASPIO = new Table ("ASPIO");	tables.add (D_ASPIO);
		Table D_ASTDO = new Table ("ASTDO");	tables.add (D_ASTDO);
		Table D_ASTMO = new Table ("ASTMO");	tables.add (D_ASTMO);
		Table D_ASTMS = new Table ("ASTMS");	tables.add (D_ASTMS);
		Table D_ATADL = new Table ("ATADL");	tables.add (D_ATADL);
		Table D_ATADO = new Table ("ATADO");	tables.add (D_ATADO);
		Table D_ATAHL = new Table ("ATAHL");	tables.add (D_ATAHL);
		Table D_ATAHS = new Table ("ATAHS");	tables.add (D_ATAHS);
		Table D_ATAXL = new Table ("ATAXL");	tables.add (D_ATAXL);
		Table D_ATAXO = new Table ("ATAXO");	tables.add (D_ATAXO);
		Table D_ATDCL = new Table ("ATDCL");	tables.add (D_ATDCL);
		Table D_ATDCS = new Table ("ATDCS");	tables.add (D_ATDCS);
		Table D_ATMPO = new Table ("ATMPO");	tables.add (D_ATMPO);
		Table D_ATRCL = new Table ("ATRCL");	tables.add (D_ATRCL);
		Table D_ATRCS = new Table ("ATRCS");	tables.add (D_ATRCS);
		Table D_ATRDO = new Table ("ATRDO");	tables.add (D_ATRDO);
		Table D_ATRTL = new Table ("ATRTL");	tables.add (D_ATRTL);
		Table D_ATRTS = new Table ("ATRTS");	tables.add (D_ATRTS);
		Table D_ATXCL = new Table ("ATXCL");	tables.add (D_ATXCL);
		Table D_ATXCO = new Table ("ATXCO");	tables.add (D_ATXCO);
		Table D_BASWO = new Table ("BASWO");	tables.add (D_BASWO);
		Table D_BATCH = new Table ("BATCH");	tables.add (D_BATCH);
		Table D_BBOKS = new Table ("BBOKS");	tables.add (D_BBOKS);
		Table D_BDLAO = new Table ("BDLAO");	tables.add (D_BDLAO);
		Table D_BDLGL = new Table ("BDLGL");	tables.add (D_BDLGL);
		Table D_BDLGO = new Table ("BDLGO");	tables.add (D_BDLGO);
		Table D_BDLPO = new Table ("BDLPO");	tables.add (D_BDLPO);
		Table D_BDLTO = new Table ("BDLTO");	tables.add (D_BDLTO);
		Table D_BICTO = new Table ("BICTO");	tables.add (D_BICTO);
		Table D_BISPO = new Table ("BISPO");	tables.add (D_BISPO);
		Table D_BISSO = new Table ("BISSO");	tables.add (D_BISSO);
		Table D_BITTO = new Table ("BITTO");	tables.add (D_BITTO);
		Table D_BPOTO = new Table ("BPOTO");	tables.add (D_BPOTO);
		Table D_BPRDO = new Table ("BPRDO");	tables.add (D_BPRDO);
		Table D_BREDO = new Table ("BREDO");	tables.add (D_BREDO);
		Table D_BREPO = new Table ("BREPO");	tables.add (D_BREPO);
		Table D_BRPIO = new Table ("BRPIO");	tables.add (D_BRPIO);
		Table D_BSPIO = new Table ("BSPIO");	tables.add (D_BSPIO);
		Table D_CBFMO = new Table ("CBFMO");	tables.add (D_CBFMO);
		Table D_CC3PL = new Table ("CC3PL");	tables.add (D_CC3PL);
		Table D_CC3PO = new Table ("CC3PO");	tables.add (D_CC3PO);
		Table D_CCBRO = new Table ("CCBRO");	tables.add (D_CCBRO);
		Table D_CCDIL = new Table ("CCDIL");	tables.add (D_CCDIL);
		Table D_CCDIS = new Table ("CCDIS");	tables.add (D_CCDIS);
		Table D_CCFPO = new Table ("CCFPO");	tables.add (D_CCFPO);
		Table D_CCGRL = new Table ("CCGRL");	tables.add (D_CCGRL);
		Table D_CCGRS = new Table ("CCGRS");	tables.add (D_CCGRS);
		Table D_CCMHL = new Table ("CCMHL");	tables.add (D_CCMHL);
		Table D_CCMHO = new Table ("CCMHO");	tables.add (D_CCMHO);
		Table D_CCOCL = new Table ("CCOCL");	tables.add (D_CCOCL);
		Table D_CCOCO = new Table ("CCOCO");	tables.add (D_CCOCO);
		Table D_CCOML = new Table ("CCOML");	tables.add (D_CCOML);
		Table D_CCOMS = new Table ("CCOMS");	tables.add (D_CCOMS);
		Table D_CCORL = new Table ("CCORL");	tables.add (D_CCORL);
		Table D_CCORO = new Table ("CCORO");	tables.add (D_CCORO);
		Table D_CCREO = new Table ("CCREO");	tables.add (D_CCREO);
		Table D_CCRXO = new Table ("CCRXO");	tables.add (D_CCRXO);
		Table D_CCUSO = new Table ("CCUSO");	tables.add (D_CCUSO);
		Table D_CDFAO = new Table ("CDFAO");	tables.add (D_CDFAO);
		Table D_CDIAL = new Table ("CDIAL");	tables.add (D_CDIAL);
		Table D_CDIAO = new Table ("CDIAO");	tables.add (D_CDIAO);
		Table D_CEXPS = new Table ("CEXPS");	tables.add (D_CEXPS);
		Table D_CEXRO = new Table ("CEXRO");	tables.add (D_CEXRO);
		Table D_CEXSO = new Table ("CEXSO");	tables.add (D_CEXSO);
		Table D_CFACL = new Table ("CFACL");	tables.add (D_CFACL);
		Table D_CFACO = new Table ("CFACO");	tables.add (D_CFACO);
		Table D_CFACS = new Table ("CFACS");	tables.add (D_CFACS);
		Table D_CFAIO = new Table ("CFAIO");	tables.add (D_CFAIO);
		Table D_CFATO = new Table ("CFATO");	tables.add (D_CFATO);
		Table D_CFCRO = new Table ("CFCRO");	tables.add (D_CFCRO);
		Table D_CFDDL = new Table ("CFDDL");	tables.add (D_CFDDL);
		Table D_CFDDO = new Table ("CFDDO");	tables.add (D_CFDDO);
		Table D_CFFEO = new Table ("CFFEO");	tables.add (D_CFFEO);
		Table D_CFRSL = new Table ("CFRSL");	tables.add (D_CFRSL);
		Table D_CFRSO = new Table ("CFRSO");	tables.add (D_CFRSO);
		Table D_CFUPO = new Table ("CFUPO");	tables.add (D_CFUPO);
		Table D_CFUSO = new Table ("CFUSO");	tables.add (D_CFUSO);
		Table D_CFXRO = new Table ("CFXRO");	tables.add (D_CFXRO);
		Table D_CGATO = new Table ("CGATO");	tables.add (D_CGATO);
		Table D_CGUAL = new Table ("CGUAL");	tables.add (D_CGUAL);
		Table D_CGUAS = new Table ("CGUAS");	tables.add (D_CGUAS);
		Table D_CGUCL = new Table ("CGUCL");	tables.add (D_CGUCL);
		Table D_CGUCO = new Table ("CGUCO");	tables.add (D_CGUCO);
		Table D_CGURL = new Table ("CGURL");	tables.add (D_CGURL);
		Table D_CGURO = new Table ("CGURO");	tables.add (D_CGURO);
		Table D_CHFCO = new Table ("CHFCO");	tables.add (D_CHFCO);
		Table D_CMHIO = new Table ("CMHIO");	tables.add (D_CMHIO);
		Table D_CMRGL = new Table ("CMRGL");	tables.add (D_CMRGL);
		Table D_CMRGO = new Table ("CMRGO");	tables.add (D_CMRGO);
		Table D_CMTBL = new Table ("CMTBL");	tables.add (D_CMTBL);
		Table D_CMTBO = new Table ("CMTBO");	tables.add (D_CMTBO);
		Table D_CMTCO = new Table ("CMTCO");	tables.add (D_CMTCO);
		Table D_CMTCS = new Table ("CMTCS");	tables.add (D_CMTCS);
		Table D_CMTIO = new Table ("CMTIO");	tables.add (D_CMTIO);
		Table D_CMTLL = new Table ("CMTLL");	tables.add (D_CMTLL);
		Table D_CMTLO = new Table ("CMTLO");	tables.add (D_CMTLO);
		Table D_COMSO = new Table ("COMSO");	tables.add (D_COMSO);
		Table D_CPBCO = new Table ("CPBCO");	tables.add (D_CPBCO);
		Table D_CPBGO = new Table ("CPBGO");	tables.add (D_CPBGO);
		Table D_CPBPO = new Table ("CPBPO");	tables.add (D_CPBPO);
		Table D_CPTIL = new Table ("CPTIL");	tables.add (D_CPTIL);
		Table D_CPTIO = new Table ("CPTIO");	tables.add (D_CPTIO);
		Table D_CREPL = new Table ("CREPL");	tables.add (D_CREPL);
		Table D_CREPO = new Table ("CREPO");	tables.add (D_CREPO);
		Table D_CREXO = new Table ("CREXO");	tables.add (D_CREXO);
		Table D_CRISO = new Table ("CRISO");	tables.add (D_CRISO);
		Table D_CROCL = new Table ("CROCL");	tables.add (D_CROCL);
		Table D_CROCO = new Table ("CROCO");	tables.add (D_CROCO);
		Table D_CRTRO = new Table ("CRTRO");	tables.add (D_CRTRO);
		Table D_CRXMO = new Table ("CRXMO");	tables.add (D_CRXMO);
		Table D_CSCOL = new Table ("CSCOL");	tables.add (D_CSCOL);
		Table D_CSCOO = new Table ("CSCOO");	tables.add (D_CSCOO);
		Table D_CSECL = new Table ("CSECL");	tables.add (D_CSECL);
		Table D_CSECS = new Table ("CSECS");	tables.add (D_CSECS);
		Table D_CSEIO = new Table ("CSEIO");	tables.add (D_CSEIO);
		Table D_CSHAL = new Table ("CSHAL");	tables.add (D_CSHAL);
		Table D_CSHAO = new Table ("CSHAO");	tables.add (D_CSHAO);
		Table D_CSHCO = new Table ("CSHCO");	tables.add (D_CSHCO);
		Table D_CSHDS = new Table ("CSHDS");	tables.add (D_CSHDS);
		Table D_CSHML = new Table ("CSHML");	tables.add (D_CSHML);
		Table D_CSHMO = new Table ("CSHMO");	tables.add (D_CSHMO);
		Table D_CSHOO = new Table ("CSHOO");	tables.add (D_CSHOO);
		Table D_CSHRO = new Table ("CSHRO");	tables.add (D_CSHRO);
		Table D_CSHTL = new Table ("CSHTL");	tables.add (D_CSHTL);
		Table D_CSHTO = new Table ("CSHTO");	tables.add (D_CSHTO);
		Table D_CSHVO = new Table ("CSHVO");	tables.add (D_CSHVO);
		Table D_CSIMS = new Table ("CSIMS");	tables.add (D_CSIMS);
		Table D_CSLFO = new Table ("CSLFO");	tables.add (D_CSLFO);
		Table D_CSSHL = new Table ("CSSHL");	tables.add (D_CSSHL);
		Table D_CSSHO = new Table ("CSSHO");	tables.add (D_CSSHO);
		Table D_DBATO = new Table ("DBATO");	tables.add (D_DBATO);
		Table D_DBILO = new Table ("DBILO");	tables.add (D_DBILO);
		Table D_DCBDO = new Table ("DCBDO");	tables.add (D_DCBDO);
		Table D_DCBMO = new Table ("DCBMO");	tables.add (D_DCBMO);
		Table D_DCBRO = new Table ("DCBRO");	tables.add (D_DCBRO);
		Table D_DCLHO = new Table ("DCLHO");	tables.add (D_DCLHO);
		Table D_DCLTO = new Table ("DCLTO");	tables.add (D_DCLTO);
		Table D_DCNDL = new Table ("DCNDL");	tables.add (D_DCNDL);
		Table D_DCNDO = new Table ("DCNDO");	tables.add (D_DCNDO);
		Table D_DCNIO = new Table ("DCNIO");	tables.add (D_DCNIO);
		Table D_DCNTL = new Table ("DCNTL");	tables.add (D_DCNTL);
		Table D_DCNTO = new Table ("DCNTO");	tables.add (D_DCNTO);
		Table D_DCONO = new Table ("DCONO");	tables.add (D_DCONO);
		Table D_DCOVO = new Table ("DCOVO");	tables.add (D_DCOVO);
		Table D_DCPOO = new Table ("DCPOO");	tables.add (D_DCPOO);
		Table D_DCTIO = new Table ("DCTIO");	tables.add (D_DCTIO);
		Table D_DCTRO = new Table ("DCTRO");	tables.add (D_DCTRO);
		Table D_DDDCL = new Table ("DDDCL");	tables.add (D_DDDCL);
		Table D_DDDCO = new Table ("DDDCO");	tables.add (D_DDDCO);
		Table D_DDETO = new Table ("DDETO");	tables.add (D_DDETO);
		Table D_DEBFO = new Table ("DEBFO");	tables.add (D_DEBFO);
		Table D_DEFIN = new Table ("DEFIN");	tables.add (D_DEFIN);
		Table D_DFIDO = new Table ("DFIDO");	tables.add (D_DFIDO);
		Table D_DFIMO = new Table ("DFIMO");	tables.add (D_DFIMO);
		Table D_DFRTO = new Table ("DFRTO");	tables.add (D_DFRTO);
		Table D_DFSCI = new Table ("DFSCI");	tables.add (D_DFSCI);
		Table D_DFSCL = new Table ("DFSCL");	tables.add (D_DFSCL);
		Table D_DFSCO = new Table ("DFSCO");	tables.add (D_DFSCO);
		Table D_DFSII = new Table ("DFSII");	tables.add (D_DFSII);
		Table D_DFSIL = new Table ("DFSIL");	tables.add (D_DFSIL);
		Table D_DFSIO = new Table ("DFSIO");	tables.add (D_DFSIO);
		Table D_DGCAL = new Table ("DGCAL");	tables.add (D_DGCAL);
		Table D_DGCAO = new Table ("DGCAO");	tables.add (D_DGCAO);
		Table D_DIPTL = new Table ("DIPTL");	tables.add (D_DIPTL);
		Table D_DIPTO = new Table ("DIPTO");	tables.add (D_DIPTO);
		Table D_DMSTO = new Table ("DMSTO");	tables.add (D_DMSTO);
		Table D_DPBDO = new Table ("DPBDO");	tables.add (D_DPBDO);
		Table D_DPFBK = new Table ("DPFBK");	tables.add (D_DPFBK);
		Table D_DPTRL = new Table ("DPTRL");	tables.add (D_DPTRL);
		Table D_DPTRO = new Table ("DPTRO");	tables.add (D_DPTRO);
		Table D_DRDIL = new Table ("DRDIL");	tables.add (D_DRDIL);
		Table D_DRDIO = new Table ("DRDIO");	tables.add (D_DRDIO);
		Table D_DRPTO = new Table ("DRPTO");	tables.add (D_DRPTO);
		Table D_DRSDO = new Table ("DRSDO");	tables.add (D_DRSDO);
		Table D_DRSMO = new Table ("DRSMO");	tables.add (D_DRSMO);
		Table D_DSLVO = new Table ("DSLVO");	tables.add (D_DSLVO);
		Table D_DSTAO = new Table ("DSTAO");	tables.add (D_DSTAO);
		Table D_DSTIO = new Table ("DSTIO");	tables.add (D_DSTIO);
		Table D_DSTOL = new Table ("DSTOL");	tables.add (D_DSTOL);
		Table D_DSTOO = new Table ("DSTOO");	tables.add (D_DSTOO);
		Table D_DSTSO = new Table ("DSTSO");	tables.add (D_DSTSO);
		Table D_GBTDS = new Table ("GBTDS");	tables.add (D_GBTDS);
		Table D_GBTPS = new Table ("GBTPS");	tables.add (D_GBTPS);
		Table D_GBTRO = new Table ("GBTRO");	tables.add (D_GBTRO);
		Table D_GCNVO = new Table ("GCNVO");	tables.add (D_GCNVO);
		Table D_GMONO = new Table ("GMONO");	tables.add (D_GMONO);
		Table D_GMSGS = new Table ("GMSGS");	tables.add (D_GMSGS);
		Table D_GPAIO = new Table ("GPAIO");	tables.add (D_GPAIO);
		Table D_GPARL = new Table ("GPARL");	tables.add (D_GPARL);
		Table D_GPARO = new Table ("GPARO");	tables.add (D_GPARO);
		Table D_GPRTO = new Table ("GPRTO");	tables.add (D_GPRTO);
		Table D_GRTPO = new Table ("GRTPO");	tables.add (D_GRTPO);
		Table D_GSADO = new Table ("GSADO");	tables.add (D_GSADO);
		Table D_GSAMO = new Table ("GSAMO");	tables.add (D_GSAMO);
		Table D_GSBRO = new Table ("GSBRO");	tables.add (D_GSBRO);
		Table D_GSBSO = new Table ("GSBSO");	tables.add (D_GSBSO);
		Table D_GSEML = new Table ("GSEML");	tables.add (D_GSEML);
		Table D_GSEMO = new Table ("GSEMO");	tables.add (D_GSEMO);
		Table D_GSWMO = new Table ("GSWMO");	tables.add (D_GSWMO);
		Table D_GXMLO = new Table ("GXMLO");	tables.add (D_GXMLO);
		Table D_IACCO = new Table ("IACCO");	tables.add (D_IACCO);
		Table D_IAUTL = new Table ("IAUTL");	tables.add (D_IAUTL);
		Table D_IAUTO = new Table ("IAUTO");	tables.add (D_IAUTO);
		Table D_IBATO = new Table ("IBATO");	tables.add (D_IBATO);
		Table D_IBBDO = new Table ("IBBDO");	tables.add (D_IBBDO);
		Table D_IBDDO = new Table ("IBDDO");	tables.add (D_IBDDO);
		Table D_IBDHO = new Table ("IBDHO");	tables.add (D_IBDHO);
		Table D_ICBRO = new Table ("ICBRO");	tables.add (D_ICBRO);
		Table D_ICNDL = new Table ("ICNDL");	tables.add (D_ICNDL);
		Table D_ICNDO = new Table ("ICNDO");	tables.add (D_ICNDO);
		Table D_ICNFL = new Table ("ICNFL");	tables.add (D_ICNFL);
		Table D_ICNFO = new Table ("ICNFO");	tables.add (D_ICNFO);
		Table D_ICNFS = new Table ("ICNFS");	tables.add (D_ICNFS);
		Table D_ICOPL = new Table ("ICOPL");	tables.add (D_ICOPL);
		Table D_ICOPO = new Table ("ICOPO");	tables.add (D_ICOPO);
		Table D_ICVBS = new Table ("ICVBS");	tables.add (D_ICVBS);
		Table D_IDPLO = new Table ("IDPLO");	tables.add (D_IDPLO);
		Table D_IERBS = new Table ("IERBS");	tables.add (D_IERBS);
		Table D_IERMO = new Table ("IERMO");	tables.add (D_IERMO);
		Table D_IFASO = new Table ("IFASO");	tables.add (D_IFASO);
		Table D_IFCPO = new Table ("IFCPO");	tables.add (D_IFCPO);
		Table D_IFCSL = new Table ("IFCSL");	tables.add (D_IFCSL);
		Table D_IFCSO = new Table ("IFCSO");	tables.add (D_IFCSO);
		Table D_IFFAO = new Table ("IFFAO");	tables.add (D_IFFAO);
		Table D_IFFCL = new Table ("IFFCL");	tables.add (D_IFFCL);
		Table D_IFFCO = new Table ("IFFCO");	tables.add (D_IFFCO);
		Table D_IFFDL = new Table ("IFFDL");	tables.add (D_IFFDL);
		Table D_IFFDO = new Table ("IFFDO");	tables.add (D_IFFDO);
		Table D_IFFOO = new Table ("IFFOO");	tables.add (D_IFFOO);
		Table D_IFFPO = new Table ("IFFPO");	tables.add (D_IFFPO);
		Table D_IFFTO = new Table ("IFFTO");	tables.add (D_IFFTO);
		Table D_IFFVO = new Table ("IFFVO");	tables.add (D_IFFVO);
		Table D_IFPSO = new Table ("IFPSO");	tables.add (D_IFPSO);
		Table D_IFRAL = new Table ("IFRAL");	tables.add (D_IFRAL);
		Table D_IFRAO = new Table ("IFRAO");	tables.add (D_IFRAO);
		Table D_IFRCL = new Table ("IFRCL");	tables.add (D_IFRCL);
		Table D_IFRCO = new Table ("IFRCO");	tables.add (D_IFRCO);
		Table D_IFRPL = new Table ("IFRPL");	tables.add (D_IFRPL);
		Table D_IFRPO = new Table ("IFRPO");	tables.add (D_IFRPO);
		Table D_IFTCO = new Table ("IFTCO");	tables.add (D_IFTCO);
		Table D_IFTSO = new Table ("IFTSO");	tables.add (D_IFTSO);
		Table D_IFUPS = new Table ("IFUPS");	tables.add (D_IFUPS);
		Table D_IFXAO = new Table ("IFXAO");	tables.add (D_IFXAO);
		Table D_IFXCL = new Table ("IFXCL");	tables.add (D_IFXCL);
		Table D_IFXCO = new Table ("IFXCO");	tables.add (D_IFXCO);
		Table D_IFXPL = new Table ("IFXPL");	tables.add (D_IFXPL);
		Table D_IFXPO = new Table ("IFXPO");	tables.add (D_IFXPO);
		Table D_IGIRO = new Table ("IGIRO");	tables.add (D_IGIRO);
		Table D_IGLDO = new Table ("IGLDO");	tables.add (D_IGLDO);
		Table D_IHMCL = new Table ("IHMCL");	tables.add (D_IHMCL);
		Table D_IHMCS = new Table ("IHMCS");	tables.add (D_IHMCS);
		Table D_IICSO = new Table ("IICSO");	tables.add (D_IICSO);
		Table D_IIFFO = new Table ("IIFFO");	tables.add (D_IIFFO);
		Table D_IIFRO = new Table ("IIFRO");	tables.add (D_IIFRO);
		Table D_IIFXO = new Table ("IIFXO");	tables.add (D_IIFXO);
		Table D_IILDO = new Table ("IILDO");	tables.add (D_IILDO);
		Table D_IINIO = new Table ("IINIO");	tables.add (D_IINIO);
		Table D_IINTL = new Table ("IINTL");	tables.add (D_IINTL);
		Table D_IINTO = new Table ("IINTO");	tables.add (D_IINTO);
		Table D_IIOPO = new Table ("IIOPO");	tables.add (D_IIOPO);
		Table D_IIPIO = new Table ("IIPIO");	tables.add (D_IIPIO);
		Table D_IIRSO = new Table ("IIRSO");	tables.add (D_IIRSO);
		Table D_ILDAO = new Table ("ILDAO");	tables.add (D_ILDAO);
		Table D_ILDCL = new Table ("ILDCL");	tables.add (D_ILDCL);
		Table D_ILDCO = new Table ("ILDCO");	tables.add (D_ILDCO);
		Table D_ILDPL = new Table ("ILDPL");	tables.add (D_ILDPL);
		Table D_ILDPO = new Table ("ILDPO");	tables.add (D_ILDPO);
		Table D_ILDTL = new Table ("ILDTL");	tables.add (D_ILDTL);
		Table D_ILNGL = new Table ("ILNGL");	tables.add (D_ILNGL);
		Table D_ILNGS = new Table ("ILNGS");	tables.add (D_ILNGS);
		Table D_ILPAL = new Table ("ILPAL");	tables.add (D_ILPAL);
		Table D_ILPAO = new Table ("ILPAO");	tables.add (D_ILPAO);
		Table D_IMAGO = new Table ("IMAGO");	tables.add (D_IMAGO);
		Table D_INETL = new Table ("INETL");	tables.add (D_INETL);
		Table D_INETO = new Table ("INETO");	tables.add (D_INETO);
		Table D_INOSL = new Table ("INOSL");	tables.add (D_INOSL);
		Table D_INOSO = new Table ("INOSO");	tables.add (D_INOSO);
		Table D_INVMO = new Table ("INVMO");	tables.add (D_INVMO);
		Table D_IOASO = new Table ("IOASO");	tables.add (D_IOASO);
		Table D_IODUL = new Table ("IODUL");	tables.add (D_IODUL);
		Table D_IODUS = new Table ("IODUS");	tables.add (D_IODUS);
		Table D_IOPCL = new Table ("IOPCL");	tables.add (D_IOPCL);
		Table D_IOPCO = new Table ("IOPCO");	tables.add (D_IOPCO);
		Table D_IOPPL = new Table ("IOPPL");	tables.add (D_IOPPL);
		Table D_IOPPO = new Table ("IOPPO");	tables.add (D_IOPPO);
		Table D_IOPTL = new Table ("IOPTL");	tables.add (D_IOPTL);
		Table D_IOPTS = new Table ("IOPTS");	tables.add (D_IOPTS);
		Table D_IPIBO = new Table ("IPIBO");	tables.add (D_IPIBO);
		Table D_IPIIO = new Table ("IPIIO");	tables.add (D_IPIIO);
		Table D_IPMTL = new Table ("IPMTL");	tables.add (D_IPMTL);
		Table D_IPMTO = new Table ("IPMTO");	tables.add (D_IPMTO);
		Table D_IPTIL = new Table ("IPTIL");	tables.add (D_IPTIL);
		Table D_IPTIO = new Table ("IPTIO");	tables.add (D_IPTIO);
		Table D_IPWDL = new Table ("IPWDL");	tables.add (D_IPWDL);
		Table D_IPWDO = new Table ("IPWDO");	tables.add (D_IPWDO);
		Table D_IRCVO = new Table ("IRCVO");	tables.add (D_IRCVO);
		Table D_IRFRL = new Table ("IRFRL");	tables.add (D_IRFRL);
		Table D_IRFRO = new Table ("IRFRO");	tables.add (D_IRFRO);
		Table D_IRSAO = new Table ("IRSAO");	tables.add (D_IRSAO);
		Table D_IRSCL = new Table ("IRSCL");	tables.add (D_IRSCL);
		Table D_IRSCO = new Table ("IRSCO");	tables.add (D_IRSCO);
		Table D_IRSPL = new Table ("IRSPL");	tables.add (D_IRSPL);
		Table D_IRSPO = new Table ("IRSPO");	tables.add (D_IRSPO);
		Table D_IRSWL = new Table ("IRSWL");	tables.add (D_IRSWL);
		Table D_ISAVO = new Table ("ISAVO");	tables.add (D_ISAVO);
		Table D_ISIFO = new Table ("ISIFO");	tables.add (D_ISIFO);
		Table D_ISIML = new Table ("ISIML");	tables.add (D_ISIML);
		Table D_ISIMO = new Table ("ISIMO");	tables.add (D_ISIMO);
		Table D_ISPIL = new Table ("ISPIL");	tables.add (D_ISPIL);
		Table D_ISPIO = new Table ("ISPIO");	tables.add (D_ISPIO);
		Table D_ISTLO = new Table ("ISTLO");	tables.add (D_ISTLO);
		Table D_ISTMO = new Table ("ISTMO");	tables.add (D_ISTMO);
		Table D_ISWCO = new Table ("ISWCO");	tables.add (D_ISWCO);
		Table D_ITCFO = new Table ("ITCFO");	tables.add (D_ITCFO);
		Table D_ITECO = new Table ("ITECO");	tables.add (D_ITECO);
		Table D_ITIRO = new Table ("ITIRO");	tables.add (D_ITIRO);
		Table D_ITITL = new Table ("ITITL");	tables.add (D_ITITL);
		Table D_ITITO = new Table ("ITITO");	tables.add (D_ITITO);
		Table D_ITLDO = new Table ("ITLDO");	tables.add (D_ITLDO);
		Table D_ITOLL = new Table ("ITOLL");	tables.add (D_ITOLL);
		Table D_ITOLO = new Table ("ITOLO");	tables.add (D_ITOLO);
		Table D_ITXTO = new Table ("ITXTO");	tables.add (D_ITXTO);
		Table D_IWARL = new Table ("IWARL");	tables.add (D_IWARL);
		Table D_IWARO = new Table ("IWARO");	tables.add (D_IWARO);
		Table D_IWODL = new Table ("IWODL");	tables.add (D_IWODL);
		Table D_IWODO = new Table ("IWODO");	tables.add (D_IWODO);
		Table D_IWSSO = new Table ("IWSSO");	tables.add (D_IWSSO);
		Table D_LBGRL = new Table ("LBGRL");	tables.add (D_LBGRL);
		Table D_LBGRS = new Table ("LBGRS");	tables.add (D_LBGRS);
		Table D_LBIRO = new Table ("LBIRO");	tables.add (D_LBIRO);
		Table D_LBRAL = new Table ("LBRAL");	tables.add (D_LBRAL);
		Table D_LBRAS = new Table ("LBRAS");	tables.add (D_LBRAS);
		Table D_LBRCL = new Table ("LBRCL");	tables.add (D_LBRCL);
		Table D_LBRCS = new Table ("LBRCS");	tables.add (D_LBRCS);
		Table D_LBROL = new Table ("LBROL");	tables.add (D_LBROL);
		Table D_LBROS = new Table ("LBROS");	tables.add (D_LBROS);
		Table D_LBSKL = new Table ("LBSKL");	tables.add (D_LBSKL);
		Table D_LBSKS = new Table ("LBSKS");	tables.add (D_LBSKS);
		Table D_LCBNL = new Table ("LCBNL");	tables.add (D_LCBNL);
		Table D_LCBNS = new Table ("LCBNS");	tables.add (D_LCBNS);
		Table D_LCCCL = new Table ("LCCCL");	tables.add (D_LCCCL);
		Table D_LCCCS = new Table ("LCCCS");	tables.add (D_LCCCS);
		Table D_LCCYL = new Table ("LCCYL");	tables.add (D_LCCYL);
		Table D_LCCYS = new Table ("LCCYS");	tables.add (D_LCCYS);
		Table D_LCGRL = new Table ("LCGRL");	tables.add (D_LCGRL);
		Table D_LCGRS = new Table ("LCGRS");	tables.add (D_LCGRS);
		Table D_LCIYL = new Table ("LCIYL");	tables.add (D_LCIYL);
		Table D_LCIYS = new Table ("LCIYS");	tables.add (D_LCIYS);
		Table D_LCLIL = new Table ("LCLIL");	tables.add (D_LCLIL);
		Table D_LCLIS = new Table ("LCLIS");	tables.add (D_LCLIS);
		Table D_LCORO = new Table ("LCORO");	tables.add (D_LCORO);
		Table D_LCPSL = new Table ("LCPSL");	tables.add (D_LCPSL);
		Table D_LCPSS = new Table ("LCPSS");	tables.add (D_LCPSS);
		Table D_LCRYL = new Table ("LCRYL");	tables.add (D_LCRYL);
		Table D_LCRYS = new Table ("LCRYS");	tables.add (D_LCRYS);
		Table D_LCSTL = new Table ("LCSTL");	tables.add (D_LCSTL);
		Table D_LCSTS = new Table ("LCSTS");	tables.add (D_LCSTS);
		Table D_LCTIO = new Table ("LCTIO");	tables.add (D_LCTIO);
		Table D_LCTPL = new Table ("LCTPL");	tables.add (D_LCTPL);
		Table D_LCTPS = new Table ("LCTPS");	tables.add (D_LCTPS);
		Table D_LCTSL = new Table ("LCTSL");	tables.add (D_LCTSL);
		Table D_LCTSO = new Table ("LCTSO");	tables.add (D_LCTSO);
		Table D_LEBAO = new Table ("LEBAO");	tables.add (D_LEBAO);
		Table D_LELCL = new Table ("LELCL");	tables.add (D_LELCL);
		Table D_LELCS = new Table ("LELCS");	tables.add (D_LELCS);
		Table D_LELIO = new Table ("LELIO");	tables.add (D_LELIO);
		Table D_LGRDL = new Table ("LGRDL");	tables.add (D_LGRDL);
		Table D_LGRDO = new Table ("LGRDO");	tables.add (D_LGRDO);
		Table D_LGXFL = new Table ("LGXFL");	tables.add (D_LGXFL);
		Table D_LGXFO = new Table ("LGXFO");	tables.add (D_LGXFO);
		Table D_LHOLL = new Table ("LHOLL");	tables.add (D_LHOLL);
		Table D_LHOLO = new Table ("LHOLO");	tables.add (D_LHOLO);
		Table D_LPHIO = new Table ("LPHIO");	tables.add (D_LPHIO);
		Table D_LPHYL = new Table ("LPHYL");	tables.add (D_LPHYL);
		Table D_LPHYS = new Table ("LPHYS");	tables.add (D_LPHYS);
		Table D_LPMML = new Table ("LPMML");	tables.add (D_LPMML);
		Table D_LPRCL = new Table ("LPRCL");	tables.add (D_LPRCL);
		Table D_LPRCO = new Table ("LPRCO");	tables.add (D_LPRCO);
		Table D_LPRDL = new Table ("LPRDL");	tables.add (D_LPRDL);
		Table D_LPRDS = new Table ("LPRDS");	tables.add (D_LPRDS);
		Table D_LPRFS = new Table ("LPRFS");	tables.add (D_LPRFS);
		Table D_LPSIO = new Table ("LPSIO");	tables.add (D_LPSIO);
		Table D_LPSNL = new Table ("LPSNL");	tables.add (D_LPSNL);
		Table D_LPSNS = new Table ("LPSNS");	tables.add (D_LPSNS);
		Table D_LRCEL = new Table ("LRCEL");	tables.add (D_LRCEL);
		Table D_LRCEO = new Table ("LRCEO");	tables.add (D_LRCEO);
		Table D_LRCES = new Table ("LRCES");	tables.add (D_LRCES);
		Table D_LRCLL = new Table ("LRCLL");	tables.add (D_LRCLL);
		Table D_LRESS = new Table ("LRESS");	tables.add (D_LRESS);
		Table D_LSUPL = new Table ("LSUPL");	tables.add (D_LSUPL);
		Table D_LSUPS = new Table ("LSUPS");	tables.add (D_LSUPS);
		Table D_LTARO = new Table ("LTARO");	tables.add (D_LTARO);
		Table D_MNDEF = new Table ("MNDEF");	tables.add (D_MNDEF);
		Table D_NWSMN = new Table ("NWSMN");	tables.add (D_NWSMN);
		Table D_PRNTR = new Table ("PRNTR");	tables.add (D_PRNTR);
		Table D_PRSIO = new Table ("PRSIO");	tables.add (D_PRSIO);
		Table D_PRSNL = new Table ("PRSNL");	tables.add (D_PRSNL);
		Table D_RCHDS = new Table ("RCHDS");	tables.add (D_RCHDS);
		Table D_RCHQS = new Table ("RCHQS");	tables.add (D_RCHQS);
		Table D_RCHRO = new Table ("RCHRO");	tables.add (D_RCHRO);
		Table D_REFFL = new Table ("REFFL");	tables.add (D_REFFL);
		Table D_RPDEF = new Table ("RPDEF");	tables.add (D_RPDEF);
		Table D_RPSCH = new Table ("RPSCH");	tables.add (D_RPSCH);
		Table D_RSTRT = new Table ("RSTRT");	tables.add (D_RSTRT);
		Table D_SECIO = new Table ("SECIO");	tables.add (D_SECIO);
		Table D_SECRL = new Table ("SECRL");	tables.add (D_SECRL);
		Table D_SECUR = new Table ("SECUR");	tables.add (D_SECUR);
		Table D_TSHNO = new Table ("TSHNO");	tables.add (D_TSHNO);
		Table D_XFXAS = new Table ("XFXAS");	tables.add (D_XFXAS);
		Table D_XFXCO = new Table ("XFXCO");	tables.add (D_XFXCO);
		Table D_XFXTO = new Table ("XFXTO");	tables.add (D_XFXTO);
		Table D_XGBAO = new Table ("XGBAO");	tables.add (D_XGBAO);
		Table D_XGBUL = new Table ("XGBUL");	tables.add (D_XGBUL);
		Table D_XGBUO = new Table ("XGBUO");	tables.add (D_XGBUO);
		Table D_XGDCL = new Table ("XGDCL");	tables.add (D_XGDCL);
		Table D_XGDCS = new Table ("XGDCS");	tables.add (D_XGDCS);
		Table D_XGMVL = new Table ("XGMVL");	tables.add (D_XGMVL);
		Table D_XGMVO = new Table ("XGMVO");	tables.add (D_XGMVO);
		Table D_XGPAL = new Table ("XGPAL");	tables.add (D_XGPAL);
		Table D_XGPAO = new Table ("XGPAO");	tables.add (D_XGPAO);
		Table D_XGPML = new Table ("XGPML");	tables.add (D_XGPML);
		Table D_XGPMO = new Table ("XGPMO");	tables.add (D_XGPMO);
		Table D_XHVBO = new Table ("XHVBO");	tables.add (D_XHVBO);

		
	}
			
	private void createBaseElementsPrograms(ArrayList<Program> programs) {
		//ArrayList<String> outputList = new ArrayList<String>();
		try {
			// Open the file
			FileInputStream fstream = new FileInputStream(LocateResource.getResource(PROGFILE));
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] output = strLine.split(";");
				
				/*
				 * index 
				 * 	0 = Type of program (I, R, P, G) 
				 * 	1 = Program Name
				 * 
				 */
				// if (output[0].equals("P"))  {	} else 
				{
					Program  programToAdd = new Program (output[1],output[0]);	programs.add (programToAdd);	
				}

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	
	private void DefineUnitTestData
	( 	ArrayList<Table> tables,  ArrayList<Program> programs,
			ArrayList<TargetModule> ifsModules, ArrayList<TargetModule> lbbModules)
    {
		Table Table01 = new Table ("Table01");		tables.add(Table01);
		Table Table02 = new Table ("Table02");		tables.add(Table02);
		Table Table03 = new Table ("Table03");		tables.add(Table03);
		Table Table04 = new Table ("Table04");		tables.add(Table04);
		Table Table05 = new Table ("Table05");		tables.add(Table05);
		
		Program ProgramAAA = new Program ("ProgramAAA", "R"); 		programs.add(ProgramAAA);
		Program ProgramBBB = new Program ("ProgramBBB", "I"); 		programs.add(ProgramBBB);
		Program ProgramCCC = new Program ("ProgramCCC", "G"); 		programs.add(ProgramCCC);
		
		TargetModule Module001 = new TargetIFSModule ("Module001");		ifsModules.add(Module001);
		TargetModule Module002 = new TargetIFSModule ("Module002");		ifsModules.add(Module002);
		TargetModule Module003 = new TargetIFSModule ("Module003"); 		ifsModules.add(Module003);

    }
	
	
	
	
}
