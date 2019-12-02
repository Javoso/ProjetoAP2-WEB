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
import br.com.projetoWEB.model.Aluno;
import br.com.projetoWEB.model.FrequenciaAula;
import br.com.projetoWEB.model.Turma;
import br.com.projetoWEB.util.jsf.FacesUtil;

@Named
@RequestScoped
public class GraficoController implements Serializable {
	private static final long serialVersionUID = 4114036487780506154L;

	private BarChartModel modelBar = new BarChartModel();

	private PieChartModel pieModel = new PieChartModel();

	private PieChartModel pieModel2 = new PieChartModel();

	private PieChartModel pieModel3 = new PieChartModel();

	private Aluno aluno = new Aluno();

	private Turma turma = new Turma();

	private FrequenciaAula frequenciaAula = new FrequenciaAula();

	private List<FrequenciaAula> frequenciaAulasPorTurma = new ArrayList<>();

	private List<FrequenciaAula> frequenciaAulasPorAluno = new ArrayList<>();

	@Inject
	private GenericDAO<FrequenciaAula> frequenciaAulaDao = new GenericDAO<>();

	@Inject
	private GenericDAO<Aluno> alunoDao = new GenericDAO<>();

	@Inject
	private GenericDAO<Turma> turmaDao = new GenericDAO<>();

	public void preRender() {
		if (isNull(turma.getId()) && isNull(aluno.getId())) {
			adcionarValueFull("Alunoes por FrequenciaAula");
			FacesUtil.addMensagem().info("Esse é o grafíco default!").para("msg").mantendoMensagemAposRedirect();
		}
		initPieModel("FrequenciaAulas por gênero");
		initModel("FrequenciaAulas por aluno");
		initModelFrequenciaAula("Alunoes por frequenciaAula");

		FacesUtil.addMensagem().info("Grafícos de pizza foram inicializados.").para("msg")
				.mantendoMensagemAposRedirect();
	}

	public void selectTurma() {
		adcionarValueTurma("Alunoes por FrequenciaAula");
		FacesUtil.addMensagem().info("Grafíco por gênero inicializado.").para("msg").mantendoMensagemAposRedirect();
	}

	public void selectAluno() {
		adcionarValueAluno("FrequenciaAula por Aluno");
		FacesUtil.addMensagem().info("Grafíco por aluno inicializado.").para("msg").mantendoMensagemAposRedirect();
	}

	private void adcionarValueAluno(String titulo) {
		ChartSeries serie = null;
		if (nonNull(aluno.getId())) {
			frequenciaAulasPorAluno = frequenciaAulaDao.findByAtributeWithJoinStatusAtivado("frequenciaAulasPorAluno",
					"nomeAluno", aluno.getNomeAluno());
			if (nonNull(frequenciaAulasPorAluno)) {
				serie = new ChartSeries();
				serie.setLabel(aluno.getNomeAluno());
				for (FrequenciaAula frequenciaAula : frequenciaAulasPorAluno) {
					serie.set(frequenciaAula.getTurma().getNomeDaTurma(), frequenciaAula.getAlunosFrequentes().size());
				}
				this.modelBar.setTitle(titulo);
				this.modelBar.setLegendLabel("FrequenciaAulas por Aluno");
				this.modelBar.setAnimate(true);
				this.modelBar.setShowPointLabels(true);
				this.modelBar.setShowDatatip(true);
				this.modelBar.addSeries(serie);
			}
		} else {
			chartDefault(serie);
		}
	}

	private void adcionarValueTurma(String titulo) {
		ChartSeries serie = new ChartSeries(titulo);
		if (nonNull(turma.getId())) {
			frequenciaAulasPorTurma = frequenciaAulaDao.findByAtributeList(FrequenciaAula.class, "turma", turma);
			if (nonNull(frequenciaAulasPorTurma)) {
				for (FrequenciaAula frequenciaAula : frequenciaAulasPorTurma) {
					serie.setLabel(frequenciaAula.getTurma().getNomeDaTurma());
					serie.set(frequenciaAula.getTurma().getNomeDaTurma(), frequenciaAula.getAlunosFrequentes().size());
				}
				this.modelBar.setTitle(titulo);
				this.modelBar.setLegendLabel("FrequenciaAulas por Gênero");
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
		for (FrequenciaAula frequenciaAula : frequenciaAulaDao.findAll(FrequenciaAula.class)) {
			serie.setLabel(frequenciaAula.getTurma().getNomeDaTurma());
			serie.set(frequenciaAula.getTurma().getNomeDaTurma(), frequenciaAula.getAlunosFrequentes().size());
		}
		this.modelBar.setTitle(titulo);
		this.modelBar.setLegendLabel("FrequenciaAulas");
		this.modelBar.setAnimate(true);
		this.modelBar.addSeries(serie);
		this.modelBar.setShowPointLabels(true);
		this.modelBar.setShowDatatip(true);

	}

	private void initPieModel(String rotulo) {
		for (Turma turma : turmaDao.findAll(Turma.class)) {
			pieModel.set(turma.getNomeDaTurma(),
					frequenciaAulaDao.findByAtributeList(FrequenciaAula.class, "turma", turma).size());
		}
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150);
		pieModel.setShadow(false);
		pieModel.setTitle(rotulo);
	}

	private void initModel(String rotulo) {
		for (Aluno aluno : alunoDao.findAll(Aluno.class)) {
			pieModel2.set(aluno.getNomeAluno(), frequenciaAulaDao
					.findByAtributeWithJoinStatusAtivado("frequenciaAulasPorAluno", "nomeAluno", aluno.getNomeAluno())
					.size());
		}
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
		pieModel2.setShadow(false);
		pieModel2.setTitle(rotulo);
	}

	private void initModelFrequenciaAula(String rotulo) {
		for (FrequenciaAula frequenciaAula : frequenciaAulaDao.findAll(FrequenciaAula.class)) {
			pieModel3.set(frequenciaAula.getTurma().getNomeDaTurma(), frequenciaAula.getAlunosFrequentes().size());
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public FrequenciaAula getFrequenciaAula() {
		return frequenciaAula;
	}

	public void setFrequenciaAula(FrequenciaAula frequenciaAula) {
		this.frequenciaAula = frequenciaAula;
	}
}
