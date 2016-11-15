class Node {
	private Node pai;
	private Node esquerda;
	private Node direita;
	private int chave;
	
	Node(int chave)	{
		pai = null;
		esquerda = null;
		direita = null;
		this.chave = chave;
	}

	Node getPai() {
		return pai;
	}

	void setPai(Node pai) {
		this.pai = pai;
	}

	Node getEsquerda() {
		return esquerda;
	}

	void setEsquerda(Node esquerda) {
		this.esquerda = esquerda;
	}

	Node getDireita() {
		return direita;
	}

	void setDireita(Node direita) {
		this.direita = direita;
	}

	int getChave() {
		return chave;
	}

	void setChave(int chave) {
		this.chave = chave;
	}
	
	
}
