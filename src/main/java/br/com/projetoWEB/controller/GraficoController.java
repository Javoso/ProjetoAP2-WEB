package br.com.projetoWEB.controller;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.projetoWEB.dao.GenericDAO;
import br.com.projetoWEB.model.Ator;
import br.com.projetoWEB.model.Filme;
import br.com.projetoWEB.model.Genero;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@RequestScoped
public class GraficoController implements Serializable {
	private static final long serialVersionUID = 4114036487780506154L;

	private BarChartModel modelBar = new BarChartModel();

	private PieChartModel pieModel = new PieChartModel();

	private PieChartModel pieModel2 = new PieChartModel();

	private PieChartModel pieModel3 = new PieChartModel();

	private Ator ator = new Ator();

	private Genero genero = new Genero();

	private Filme filme = new Filme();

	private List<Filme> filmesPorGenero = new ArrayList<>();

	private List<Filme> filmesPorAtor = new ArrayList<>();

	@Inject
	private GenericDAO<Filme> filmeDao = new GenericDAO<>();

	@Inject
	private GenericDAO<Ator> atorDao = new GenericDAO<>();

	@Inject
	private GenericDAO<Genero> generoDao = new GenericDAO<>();

	public void preRender() {
		if (isNull(genero.getId()) && isNull(ator.getId())) {
			adcionarValueFull("Atores por Filme");
			FacesUtil.addMensagem().info("Esse é o grafíco default!").para("msg").mantendoMensagemAposRedirect();
		}
		initPieModel("Filmes por gênero");
		initModel("Filmes por ator");
		initModelFilme("Atores por filme");

		FacesUtil.addMensagem().info("Grafícos de pizza foram inicializados.").para("msg")
				.mantendoMensagemAposRedirect();
	}

	public void selectGenero() {
		adcionarValueGenero("Atores por Filme");
		FacesUtil.addMensagem().info("Grafíco por gênero inicializado.").para("msg").mantendoMensagemAposRedirect();
	}

	public void selectAtor() {
		adcionarValueAtor("Filme por Ator");
		FacesUtil.addMensagem().info("Grafíco por ator inicializado.").para("msg").mantendoMensagemAposRedirect();
	}

	private void adcionarValueAtor(String titulo) {
		ChartSeries serie = null;
		if (nonNull(ator.getId())) {
			filmesPorAtor = filmeDao.findByAtributeWithJoinStatusAtivado("filmesPorAtor", "nomeAtor", ator.getNome());
			if (nonNull(filmesPorAtor)) {
				serie = new ChartSeries();
				serie.setLabel(ator.getNome());
				for (Filme filme : filmesPorAtor) {
					serie.set(filme.getNome(), filme.getAtores().size());
				}
				this.modelBar.setTitle(titulo);
				this.modelBar.setLegendLabel("Filmes por Ator");
				this.modelBar.setAnimate(true);
				this.modelBar.setShowPointLabels(true);
				this.modelBar.setShowDatatip(true);
				this.modelBar.addSeries(serie);
			}
		} else {
			chartDefault(serie);
		}
	}

	private void adcionarValueGenero(String titulo) {
		ChartSeries serie = new ChartSeries(titulo);
		if (nonNull(genero.getId())) {
			filmesPorGenero = filmeDao.findByAtributeList(Filme.class, "genero", genero);
			if (nonNull(filmesPorGenero)) {
				for (Filme filme : filmesPorGenero) {
					serie.setLabel(filme.getNome());
					serie.set(filme.getNome(), filme.getAtores().size());
				}
				this.modelBar.setTitle(titulo);
				this.modelBar.setLegendLabel("Filmes por Gênero");
				this.modelBar.setAnimate(true);
				this.modelBar.setShowPointLabels(true);
				this.modelBar.setShowDatatip(true);
				this.modelBar.addSeries(serie);
			}
		} else {
			chartDefault(serie);
		}
	}

	private void chartDefault(ChartSeries serie) {
		serie.set("1", Math.random() * 1000);
		serie.set("2", Math.random() * 1000);
		serie.set("3", Math.random() * 1000);
		serie.set("4", Math.random() * 1000);
		serie.set("5", Math.random() * 1000);
		serie.set("6", Math.random() * 1000);
		serie.set("7", Math.random() * 1000);
		serie.set("8", Math.random() * 1000);
		serie.set("9", Math.random() * 1000);
		serie.set("10", Math.random() * 1000);
		this.modelBar.setAnimate(true);
		this.modelBar.setShowPointLabels(true);
		this.modelBar.setShowDatatip(true);
		this.modelBar.addSeries(serie);
	}

	private void adcionarValueFull(String titulo) {
		ChartSeries serie = new ChartSeries(titulo);
		for (Filme filme : filmeDao.findAll(Filme.class)) {
			serie.setLabel(filme.getNome());
			serie.set(filme.getNome(), filme.getAtores().size());
		}
		this.modelBar.setTitle(titulo);
		this.modelBar.setLegendLabel("Filmes");
		this.modelBar.setAnimate(true);
		this.modelBar.addSeries(serie);
		this.modelBar.setShowPointLabels(true);
		this.modelBar.setShowDatatip(true);

	}

	private void initPieModel(String rotulo) {
		for (Genero genero : generoDao.findAll(Genero.class)) {
			pieModel.set(genero.getNome(), filmeDao.findByAtributeList(Filme.class, "genero", genero).size());
		}
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
		pieModel.setShadow(false);
		pieModel.setTitle(rotulo);
	}

	private void initModel(String rotulo) {
		for (Ator ator : atorDao.findAll(Ator.class)) {
			pieModel2.set(ator.getNome(),
					filmeDao.findByAtributeWithJoinStatusAtivado("filmesPorAtor", "nomeAtor", ator.getNome()).size());
		}
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
		pieModel2.setShadow(false);
		pieModel2.setTitle(rotulo);
	}

	private void initModelFilme(String rotulo) {
		for (Filme filme : filmeDao.findAll(Filme.class)) {
			pieModel3.set(filme.getNome(), filme.getAtores().size());
		}
		pieModel3.setLegendPosition("w");
		pieModel3.setFill(false);
		pieModel3.setShowDataLabels(true);
		pieModel3.setDiameter(150);
		pieModel3.setShadow(false);
		pieModel3.setTitle(rotulo);

	}

	public BarChartModel getModelBar() {
		return modelBar;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public PieChartModel getPieModel3() {
		return pieModel3;
	}

	public Ator getAtor() {
		return ator;
	}

	public void setAtor(Ator ator) {
		this.ator = ator;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
}
