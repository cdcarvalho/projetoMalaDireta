package br.com.malaDiretaService.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public final class Utilitario {

    private static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";
    public static final String DATE_MASK = DD_MM_YYYY_FORMAT;
    public static final String MES_ANO_MASK = "MM/yyyy";

    private Utilitario() {
    }

    public static void mensagemOperacaoRealizadaSucesso() {
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage(
		"Operação realizada com sucesso!"));
    }

    public static void mensagemErroOperacao() {
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage(
		"Ocorreu um erro na operação!"));
    }
    
    /**
	 * Verifica se o texto informado é uma data
	 * @param valor O texto a ser verificado
	 * @return Se o valor informado é data ou não
	 * @see SimpleDateFormat
	 */
	public static boolean isDate(final String valor) {
		final SimpleDateFormat format = new SimpleDateFormat(DATE_MASK, Locale.getDefault());
		boolean retorno;
		format.setLenient(false);
		try {
			format.parse(valor);
			retorno = true;
		} catch (ParseException e) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Verifica se o texto informado é uma representação de Sim ou Não.
	 * Case insensitive
	 * @param valor O texto a ser verificado
	 * @return Se o valor é <code>"S"</code> ou <code>"N"</code>
	 */
	public static boolean isBoolean(final String valor) {
		boolean retorno = false;
		if ("S".equalsIgnoreCase(valor) || "N".equalsIgnoreCase(valor)) {
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Verifica se o texto informado é um número
	 * @param valor O texto a ser verificado
	 * @return Se é número ou não
	 * @see Integer.parseInt
	 * @see Double.parseDouble
	 */
	public static boolean isNumber(final String valor) {
		boolean res = true;
		try {
			Integer.parseInt(valor);

			Double.parseDouble(valor);
		} catch (NumberFormatException e) {
			res = false;
		}
		return res;
	}

	/**
	 * Converte uma data em String, formatando de acordo com o formato específico
	 * @param date A data a ser formatada
	 * @param format O formato
	 * @return A data formatada
	 * @see SimpleDateFormat
	 */
	public static String dateToString(final Date date, final String format) {
		final SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		return date == null ? null : sdf.format(date);
	}


	/**
	 * Obtém uma informação específica de uma data
	 * @param data Data informada
	 * @param campo Informação da data desejada
	 * @return Valor do campo desejado
	 */
	public static int obterInfoData(final Date data, final int campo) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		return cal.get(campo);
	}

	/**
	 * Obtém a quantidade de dias entre duas datas
	 * @param dataInicio Data início de referência
	 * @param dataFim Data fim de referência
	 * @return A diferença entre estas datas, em dias
	 */
	public static double daysBetween(final Date dataInicio, final Date dataFim) {
		double result = 0;  
		long diferenca = dataFim.getTime() - dataInicio.getTime();  
		double diferencaEmDias = (diferenca /1000) / 60 / 60 /24; //resultado é diferença entre as datas em dias  
		long horasRestantes = (diferenca /1000) / 60 / 60 %24; //calcula as horas restantes  
		result = diferencaEmDias + (horasRestantes /24d); //transforma as horas restantes em fração de dias  

		return result;  
	}

	public static Date adquirirDataAtualSemHora() {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");  
		try {
			return (Date)formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			return null;
		} 
	}

	public static String retiraMascaraCpfCnpj(final String valor){
		String novaStringFmt = Normalizer.normalize(valor, Normalizer.Form.NFD);
		novaStringFmt = retiraString(valor);
		return novaStringFmt;
	}

	public static String retiraString(final String valor) {
		String retorno;
		if (null == valor) {
			retorno = null;
		} else {
			String novaStringFmt = Normalizer.normalize(valor, Normalizer.Form.NFD);
			novaStringFmt = valor.replaceAll("[^0-9]", "");
			retorno = novaStringFmt;
		}
		return retorno;
	}


	public static String formataMilhar(String valor) {

		if (valor == null || valor.isEmpty() || valor.equals("null"))
		{
			return null;
		}

		double d = 0;				   
		String S = valor;
		d = Double.valueOf(S).doubleValue();
		//formata o valor em reais
		NumberFormat nf = NumberFormat.getInstance();
		//converte um valor para String
		String valorFormatado = nf.format(d);
		int posicao = valorFormatado.indexOf(",");
		String fim = valorFormatado.substring(posicao + 1, valorFormatado.length());
		//acrescenta valor decimal caso não tenha
		if (posicao < 0){

			//quando tiver uma casa decimal será acrescentado zero no final
		}else{
			for(int i = fim.length(); i<2; i++){
				valorFormatado += "0";
			}
		}

		return valorFormatado;
	}

	/**
	 * Metodo que retorna o valor monetário a partir do parâmetro que é passado pelo Oracle<br>
	 * Exemplo: <br> 
	 * parâmetro de entrada: 500 -> saída: 500,00  <br>
	 * parâmetro de entrada: 500.1 -> saída: 500,10  <br>
	 * parâmetro de entrada: 500000000.11 -> saída: 500.000.000,11  <br>.
	 */
	public static String formataMoeda(BigDecimal valor) {
		return formataMoeda(valor.toString());
	}
	
	public static String formataMoedaComNulo(Number valor) {
		String valorFormatado = "0,00";
		
		if(valor != null) {
			valorFormatado = formataMoeda(valor.toString()); 
		}
			
		return valorFormatado;
	}
	
	public static String formataMoedaComNulo(Double valor) {
		return formataMoedaComNulo(new BigDecimal(valor));
	}

	public static String formataMoeda(String valor) {

		if (valor == null || valor.isEmpty() || valor.equals("null")) {
			return null;
		}

		double d = 0;				 
		String S = valor;
		d = Double.valueOf(S).doubleValue();
		//formata o valor em reais
		NumberFormat nf = NumberFormat.getInstance();
		//converte um valor para String
		String valorFormatado = nf.format(d);
		int posicao = valorFormatado.indexOf(",");
		String fim = valorFormatado.substring(posicao + 1, valorFormatado.length());
		//acrescenta valor decimal caso não tenha
		if (posicao < 0){
			valorFormatado += ",00";
			//quando tiver uma casa decimal será acrescentado zero no final
		} else {
			if (fim.length() > 2){
				valorFormatado = valorFormatado.substring(0, posicao + 3);
			} else{
				for(int i = fim.length(); i<2; i++){
					valorFormatado += "0";
				}
			}
		}
		return valorFormatado;
	}

	public static String formatarDataParaPtBR(Date data)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(DD_MM_YYYY_FORMAT);
		return formatter.format(data);
	}

	public static String formatarDataParaPtBRCurto(String data)
	{
		String[] sDate = data.substring(0, 10).split("-");

		return sDate[2]+"/"+sDate[1]+"/"+sDate[0].substring(2);
	}

	public static String formatarDataParaMesAno(Date data)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		return formatter.format(data);
	}

	public static String formatarDataParaDiaMesAnoHoraMinuto(Date data)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
		return formatter.format(data);
	}

	public static String formarNumeroAdicionandoZero(int numero)
	{
		String num = numero+"";
		if(num.length() == 1)
			num = "0"+num;

		return num;
	}

	public static String formarNumeroAdicionandoZero(long numero)
	{
		String num = numero+"";
		if(num.length() == 1)
			num = "0"+num;

		return num;
	}

	// Método adiconar/subtrair quantidade de mes(es) de uma data informada
	public static Date addSubtrairMesData(Date date, int qtd)
	{
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);  
		calendar.add(Calendar.MONTH, +qtd);  

		return calendar.getTime();
	}  	


	/**
	 * Recebe uma string e retorna uma data
	 * @param dia Dia
	 * @param mes Mês
	 * @param ano Ano
	 * @return Data resultante
	 */
	public static Date stringToDate(String valor) throws ParseException {
		String formato = DD_MM_YYYY_FORMAT;  
		Date date = new SimpleDateFormat(formato).parse(valor);
		return date;  
	}

	public static Date removerTime(Date data)
	{
		try {
			return stringToDate(formatarDataParaPtBR(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<String> adquirirDatasEntre(String iniMesAno, String fimMesAno, boolean considerarPeriodo)
	{
		ArrayList<String> list = new ArrayList<String>();

		String[] sI = iniMesAno.split("/");
		String[] sF = fimMesAno.split("/");

		int iniMes = Integer.parseInt(sI[0]);
		int iniAno = Integer.parseInt(sI[1]);

		int fimMes = Integer.parseInt(sF[0]);
		int fimAno = Integer.parseInt(sF[1]);

		if(considerarPeriodo)
		{
			list.add("01/"+Utilitario.formarNumeroAdicionandoZero(iniMes)+"/"+Utilitario.formarNumeroAdicionandoZero(iniAno));
		}

		while(true)
		{
			++iniMes;

			if(iniMes == 13 && iniAno < fimAno)
			{
				iniMes = 1;
				++iniAno;
			}

			if(iniMes == fimMes && iniAno == fimAno)
			{
				break;
			}

			list.add("01/"+Utilitario.formarNumeroAdicionandoZero(iniMes)+"/"+Utilitario.formarNumeroAdicionandoZero(iniAno));
		}

		if(considerarPeriodo)
		{
			list.add("01/"+Utilitario.formarNumeroAdicionandoZero(fimMes)+"/"+Utilitario.formarNumeroAdicionandoZero(fimAno));
		}

		return list;
	}

	public static Date somarDia(Date data, int quantidade)
	{
		Calendar agora = Calendar.getInstance();
		agora.setTime(data);
		agora.add(Calendar.DAY_OF_MONTH, quantidade);
		return agora.getTime();	    
	}

	public static Date somarMes(Date data, int quantidade)
	{
		Calendar agora = Calendar.getInstance();
		agora.setTime(data);
		agora.add(Calendar.MONTH, quantidade);
		return agora.getTime();
	}

	public static int getDiaSemana(Date data)
	{
		Calendar agora = Calendar.getInstance();
		agora.setTime(data);

		return agora.get(Calendar.DAY_OF_WEEK);
	}

	public static boolean isFinalSemana(Date data)
	{
		int dayWeek = getDiaSemana(data);		
		return dayWeek == Calendar.SUNDAY || dayWeek == Calendar.SATURDAY;		
	}

	public static boolean validaString(String arg) {
		return arg != null && !"".equals(arg);
	}

	public static boolean validaNumber(Number numero) {
		return numero != null && numero.doubleValue() != 0.0;
	}

	public static boolean validaArray(Object[] array) {
		return array != null && array.length > 0;
	}

	public static boolean validaColecao(Collection<?> colecao) {
		return colecao != null && !colecao.isEmpty();
	}
	public static boolean isNegative(BigDecimal b) {
		return b.signum() == -1;
	}	
	public static boolean validaBigDecimalPositivo(BigDecimal b) {
		return b != null && b.signum() == 1;
	}	

	/**
	 * Verifica se o texto informado é um CNPJ
	 * @param cnpj O CNPJ a ser verificado
	 * @return Se é CNPJ ou não
	 */
	public static boolean isCnpj(final String cnpj) {
		boolean retorno = true;
		if ((cnpj == null) || (cnpj.length() != 14)) {
			retorno = false;
		}
		return retorno;
	}


	public static Date ultimoDiaMes(Date data) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(data);
		int dia = cal.getActualMaximum( Calendar.DAY_OF_MONTH );
		int mes = (cal.get(Calendar.MONDAY)+1);
		int ano = cal.get(Calendar.YEAR);
		try {
			data = (new SimpleDateFormat(DD_MM_YYYY_FORMAT)).parse( dia+"/"+mes+"/"+ano );
			return data;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date ajustaHora(Date dtSemHora){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtSemHora);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();                
	}

	public static String formatarCpfCnpj(String cpfCnpj) {  
		JFormattedTextField cnpj_cpf = new JFormattedTextField();  

		String valor = cpfCnpj.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "").replaceAll(" ", "");  
		if (valor.length() == 11) {  
			try {  
				cnpj_cpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));  
			} catch (ParseException ex) {  
				ex.printStackTrace();  
			}  
		} else {  
			try {  
				cnpj_cpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));  
			} catch (ParseException ex) {  
				ex.printStackTrace();
			}  
		}  
		cnpj_cpf.setText(valor);  

		return cnpj_cpf.getText();  

	}

	/**
	 * Formatar data retirando o time
	 * @param data
	 * @return Date
	 */
	public static Date dateRemoveTime(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat(DD_MM_YYYY_FORMAT);
		String dataAtualFormatadaStr = formatter.format(data);
		
		Date dataFormatada = null;
		try {
			dataFormatada = formatter.parse(dataAtualFormatadaStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataFormatada;
	}		
	
	public static Date parseDatePtBr(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY_FORMAT);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		return date;
	}

}
