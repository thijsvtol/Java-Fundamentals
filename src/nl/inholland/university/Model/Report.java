package nl.inholland.university.Model;

public class Report {

	private Integer java;
	private Integer cSharp;
	private Integer python;
	private Integer php;
	private Integer retakes;
	
	public Report(int java, int cSharp, int python, int php, int retakes) {
		// TODO Auto-generated constructor stub
		this.java = java;
		this.cSharp = cSharp;
		this.python = python;
		this.php = php;
		this.retakes = retakes;
	}
	public Integer getJava() {
		return java;
	}
	public void setJava(Integer java) {
		this.java = java;
	}
	public Integer getcSharp() {
		return cSharp;
	}
	public void setcSharp(Integer cSharp) {
		this.cSharp = cSharp;
	}
	public Integer getPython() {
		return python;
	}
	public void setPython(Integer python) {
		this.python = python;
	}
	public Integer getPhp() {
		return php;
	}
	public void setPhp(Integer php) {
		this.php = php;
	}
	public Integer getRetakes() {
		return retakes;
	}
	public void setRetakes(Integer retakes) {
		this.retakes = retakes;
	}
}
