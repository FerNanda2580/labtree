package tree;

import estrut.Tree;

public class BinarySearchTree implements Tree {

    private Node raiz;

    private class Node {
        int value;
        Node esquerda;
        Node direita;

        Node(int value) {
            this.value = value;
            this.esquerda = null;
            this.direita = null;
        }
    }

    @Override
    public boolean buscaElemento(int valor) {
        return buscaElementoRecursivo(raiz, valor);
    }

    private boolean buscaElementoRecursivo(Node node, int valor) {
        if (node == null) {
            return false;
        }

        if (valor == node.value) {
            return true;
        } else if (valor < node.value) {
            return buscaElementoRecursivo(node.esquerda, valor);
        } else {
            return buscaElementoRecursivo(node.direita, valor);
        }
    }

    @Override
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return encontraMinimo(raiz);
    }

    private int encontraMinimo(Node node) {
        if (node.esquerda == null) {
            return node.value;
        }
        return encontraMinimo(node.esquerda);
    }

    @Override
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("A árvore está vazia.");
        }
        return encontraMaximo(raiz);
    }

    private int encontraMaximo(Node node) {
        if (node.direita == null) {
            return node.value;
        }
        return encontraMaximo(node.direita);
    }

    @Override
    public void insereElemento(int valor) {
        raiz = insereRecursivo(raiz, valor);
    }

    private Node insereRecursivo(Node node, int valor) {
        if (node == null) {
            return new Node(valor);
        }

        if (valor < node.value) {
            node.esquerda = insereRecursivo(node.esquerda, valor);
        } else if (valor > node.value) {
            node.direita = insereRecursivo(node.direita, valor);
        }

        return node;
    }

    @Override
    public void remove(int valor) {
        raiz = removeRecursivo(raiz, valor);
    }

    private Node removeRecursivo(Node node, int valor) {
        if (node == null) {
            return null;
        }

        if (valor < node.value) {
            node.esquerda = removeRecursivo(node.esquerda, valor);
        } else if (valor > node.value) {
            node.direita = removeRecursivo(node.direita, valor);
        } else {
            // Caso com nenhum ou um filho
            if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            }

            // Caso com dois filhos
            node.value = encontraMinimo(node.direita);
            node.direita = removeRecursivo(node.direita, node.value);
        }

        return node;
    }

    @Override
    public int[] preOrdem() {
        return preOrdemRecursivo(raiz);
    }

    private int[] preOrdemRecursivo(Node node) {
        if (node == null) {
            return new int[]{};
        }

        int[] esquerda = preOrdemRecursivo(node.esquerda);
        int[] direita = preOrdemRecursivo(node.direita);

        int[] result = new int[esquerda.length + direita.length + 1];
        result[0] = node.value;

        System.arraycopy(esquerda, 0, result, 1, esquerda.length);
        System.arraycopy(direita, 0, result, esquerda.length + 1, direita.length);

        return result;
    }

    @Override
    public int[] emOrdem() {
        return emOrdemRecursivo(raiz);
    }

    private int[] emOrdemRecursivo(Node node) {
        if (node == null) {
            return new int[]{};
        }

        int[] esquerda = emOrdemRecursivo(node.esquerda);
        int[] direita = emOrdemRecursivo(node.direita);

        int[] result = new int[esquerda.length + direita.length + 1];

        System.arraycopy(esquerda, 0, result, 0, esquerda.length);
        result[esquerda.length] = node.value;
        System.arraycopy(direita, 0, result, esquerda.length + 1, direita.length);

        return result;
    }

    @Override
    public int[] posOrdem() {
        return posOrdemRecursivo(raiz);
    }

    private int[] posOrdemRecursivo(Node node) {
        if (node == null) {
            return new int[]{};
        }

        int[] esquerda = posOrdemRecursivo(node.esquerda);
        int[] direita = posOrdemRecursivo(node.direita);

        int[] result = new int[esquerda.length + direita.length + 1];

        System.arraycopy(esquerda, 0, result, 0, esquerda.length);
        System.arraycopy(direita, 0, result, esquerda.length, direita.length);
        result[esquerda.length + direita.length] = node.value;

        return result;
    }
}