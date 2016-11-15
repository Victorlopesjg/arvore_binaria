class ABB {
	private Node root;
	private Node result;
	
	ABB(){
		root = null;		
		result = null;
	}

	void percursoEmOrdem(Node busca){
		if (busca != null){
			percursoEmOrdem(busca.getEsquerda());
			System.out.printf(busca.getChave() + " ");
			percursoEmOrdem(busca.getDireita());
		}
	}

	void percursoPreOrdem(Node busca){
		if (busca != null){
			System.out.printf(busca.getChave() + " ");
			percursoPreOrdem(busca.getEsquerda());
			percursoPreOrdem(busca.getDireita());
		}
	}

	void percursoPosOrdem(Node busca){
		if (busca != null){
			percursoPosOrdem(busca.getEsquerda());
			percursoPosOrdem(busca.getDireita());
			System.out.printf(busca.getChave() + " ");
		}
	}
	
	Node buscachave(int chave, Node node){
		result = null;
		if(node!=null){
			if (node.getChave() == chave)
				return node;

			if(chave > node.getChave()){
				result = buscachave(chave,node.getDireita());
			}else if(chave < node.getChave()){
				result = buscachave(chave,node.getEsquerda());
			} 
		}
		return result;

	}

	Node max(Node busca){
		result = null;

		if(busca.getDireita()==null){
			result = busca;
		}else{
			max(busca.getDireita());
		}

		return result;
	}

	Node min(Node busca){
		result = null;

		if(busca.getEsquerda()==null){
			result = busca;
		}else{
			min(busca.getEsquerda());
		}

		return result;
	}
	
	Node getPredecessor(Node busca){

		if(busca.getEsquerda()!=null)
			result = max(busca.getEsquerda());
		else{
			Node b;
			b = busca;

			while(b.getChave() >= busca.getChave()){
				if(b.getPai()==null) {
					return null;
				}
				
				b = b.getPai();
			}
			
			result = b;
		}
		return result;
	}

	Node getSucessor(Node busca){

		if(busca.getDireita()!=null)
			result = min(busca.getDireita());
		else{
			Node b;
			b = busca;
			while(b.getChave() <= busca.getChave()){
				if(b.getPai()==null) {
					return null;
				}
				
				b = b.getPai();
			}
			result = b;
		}
		
		return result;
	}
	
	void insert(int chave){

		if(this.root == null){
			this.root = new Node(chave);
			this.root.setPai(null);
		}else
			insertbusca(this.root, chave);   
	}
		 
	public void insertbusca(Node node,int chave){
		if(chave < node.getChave()){
			
			if(node.getEsquerda() == null){
				Node left = new Node(chave);
				node.setEsquerda(left);
				node.getEsquerda().setPai(node);
			}else
				insertbusca(node.getEsquerda(), chave);
			
		}else if(chave > node.getChave()){
			if(node.getDireita() == null){
				Node right = new Node(chave);
				node.setDireita(right);
				node.getDireita().setPai(node);
			}else
				insertbusca(node.getDireita(), chave);

		}else
			return;
	}

	void remove(int chave){
		Node a = buscachave(chave,root);

		if (a != null){
			
			if(a.getEsquerda()==a.getDireita()){
				if(a.getPai()!=null){

					if(a.getPai().getEsquerda() == a){
						
						a.getPai().setEsquerda(null);
						a.setPai(null);
						a = null;
					}else{
						a.getPai().setDireita(null);
						a.setPai(null);
						a = null;
					}
				}else{
					root = null;
				}
				
			}
			
			else if((a.getEsquerda() != null) && (a.getDireita() == null)){
				if(a.getPai() != null){
					if(a.getPai().getEsquerda() == a){
						a.getPai().setEsquerda(a.getEsquerda());
						a.getEsquerda().setPai(a.getPai());
						a.setPai(null);
						a.setEsquerda(null);
						a = null;
					}else{
						a.getPai().setDireita(a.getEsquerda());
						a.getEsquerda().setPai(a.getPai());
						a.setPai(null);
						a.setEsquerda(null);
						a = null;
					}

				}else{
					root = a.getEsquerda();
					root.setPai(null);
					a.setEsquerda(null);
					a = null;
				}
			}

			else if((a.getEsquerda() == null) && (a.getDireita() != null)){
				if(a.getPai() != null){
					if(a.getPai().getEsquerda() == a){
						a.getPai().setEsquerda(a.getDireita());
						a.getDireita().setPai(a.getPai());
						a.setPai(null);
						a.setDireita(null);
						a = null;
					}else{
						a.getPai().setDireita(a.getDireita());
						a.getDireita().setPai(a.getPai());
						a.setPai(null);
						a.setDireita(null);
						a = null;
					}
				}else{
					root = a.getDireita();
					root.setPai(null);
					a.setDireita(null);
					a = null;
				}
			}

			else{
				Node b = getSucessor(a);
				int chaveB = b.getChave();
				remove(b.getChave());
				a.setChave(chaveB);
			}
			
		}
	}
	
	boolean arvoreDeBuscaValida(Node node){

		if(node==null){
			return true;
		}

		if((node.getEsquerda()!=null)&&(node.getEsquerda().getChave()>node.getChave()))
			return false;

		if((node.getDireita()!=null)&&(node.getDireita().getChave()<node.getChave()))
			return false;
		
		boolean a = true;
		boolean b = true;

		if(node.getEsquerda()!=null)
			a = arvoreDeBuscaValida(node.getEsquerda());

		if(node.getDireita()!=null)
			b = arvoreDeBuscaValida(node.getDireita());
		
		return a&&b;
	}

	int altura(Node no){

		if(no == null){
			return -1;
		}

		int esq = altura(no.getEsquerda());
		int dir = altura (no.getDireita());
		
		if(esq>dir)
			return esq+1;
		else
			return dir+1;
		
	}

	int quantidadeDeNosDadoAltura(int altura){
		int resultado = 0;
		
		for(int i=0; i<=altura; i++){
			resultado+=Math.pow(2, i);
		}
		
		return resultado;
	}
	
	int quantidadeNosDaArvore(Node no){

		if(no==null)
			return 0;
		
		return 1 + quantidadeNosDaArvore(no.getEsquerda()) +quantidadeNosDaArvore(no.getDireita());
		
	}

	Node getRoot(){
		return root;
	}

	boolean isCheia(Node n){

		int deveria = quantidadeDeNosDadoAltura(altura(root));
		int tem = quantidadeNosDaArvore(root);

		if(deveria == tem)
			return true;
		else
			return false;

		
	}

	void EmOrdem(Node busca, int p){

		if(busca != null){
			EmOrdem(busca.getEsquerda(), p+1);
			System.out.println(busca.getChave() + " (" + p + ")");
			EmOrdem(busca.getDireita(), p+1);
		}
	}
}
