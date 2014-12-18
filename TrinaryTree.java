public class TrinaryTree<T extends Comparable<T>> {

	private Node root;

	/**
	 * adds data to the Tree
	 * @param data
	 * @return
	 */
	public boolean add(T data) {
		Node node = root;
		if (root == null) {
			root = new Node(data);
			return true;
		}

		// go through the nodes and find out the location
		// where the node should be inserted
		while (node != null) {
			Node nodeToInsert = new Node(data);
			int compare = node.data.compareTo(data);
			if (compare > 0) {
				if (node.left == null) {
					nodeToInsert.parent = node;
					node.left = nodeToInsert;
					return true;
				}
				node = node.left;
			} else if (compare < 0) {
				if (node.right == null) {
					nodeToInsert.parent = node;
					node.right = nodeToInsert;
					return true;
				}
				node = node.right;
			} else {
				if (node.middle == null) {
					nodeToInsert.parent = node;
					node.middle = nodeToInsert;
					return true;
				}
				node = node.middle;
			}
		}
		return false;
	}

	/**
	 * deletes data from the tree
	 * @param data
	 * @return
	 */
	public boolean delete(T data) {
		Node node = root;
		if (node == null) {
			return false;
		}

		// check if root is the node to be deleted and
		// is a leaf node
		if (isLeafNode(node) && node.data.compareTo(data) == 0) {
			root = null;
			return true;
		}

		while (node != null) {
			int compare = node.data.compareTo(data);
			if (compare > 0) {
				node = node.left;
			} else if (compare < 0) {
				node = node.right;
			} else {
				// found the node to delete

				// if middle just remove the next middle node
				if (node.middle != null) {
					node.middle = node.middle.middle;
					return true;
				} else {

					// if node is leaf node just remove from parent
					if (isLeafNode(node)) {
						return removeLeafNode(node);
					}

					// if only left/right child present remove current
					// node and attach left/right child to parent
					if (hasOnlyLeftChild(node)) {
						if (node.parent == null) {
							node.left.parent = null;
							root = node.left;
							return true;
						}
						node.parent.left = node.left;
						node.left.parent = node.parent;
						return true;
					} else if (hasOnlyRightChild(node)) {
						if (node.parent == null) {
							node.right.parent = null;
							root = node.right;
							return true;
						}
						node.parent.right = node.right;
						node.right.parent = node.parent;
						return true;
					} else {
						// both child present
						// find least Node in right subtree so the
						Node n = findLeftMostNode(node.right);
						T nodeData = n.data;

						// check leftmost node in right-subtree is a leaf node
						// or not. If leaf node remove else remove and attach
						// its right child to its parent
						if (isLeafNode(n)) {
							removeLeafNode(n);
						} else {
							n.parent.left = n.right;
							n.right.parent = n.parent;
						}
						node.data = nodeData;
						return true;
					}
				}
			}

		}

		return false;
	}

	/**
	 * checks if a node is leaf
	 * 
	 * @param n
	 * @return
	 */
	private boolean isLeafNode(Node n) {
		if (n.left == null && n.middle == null && n.right == null) {
			return true;
		}
		return false;
	}

	/**
	 * removes leaf node
	 * @param n
	 * @return
	 */
	private boolean removeLeafNode(Node n) {
		int comp = n.parent.data.compareTo(n.data);
		if (comp > 0) {
			n.parent.left = null;
		} else {
			n.parent.right = null;
		}
		return true;
	}

	/**
	 * checks if a node only has a left child
	 * 
	 * @param n
	 * @return
	 */
	private boolean hasOnlyLeftChild(Node n) {
		if (n.left != null && n.middle == null && n.right == null) {
			return true;
		}
		return false;
	}

	/**
	 * checks if a node only has a right child
	 * 
	 * @param n
	 * @return
	 */
	private boolean hasOnlyRightChild(Node n) {
		if (n.right != null && n.middle == null && n.left == null) {
			return true;
		}
		return false;
	}

	/**
	 * Find left most node for a given node
	 * 
	 * @param n
	 * @return
	 */
	private Node findLeftMostNode(Node n) {
		while (n.left != null) {
			n = n.left;
		}
		return n;
	}

	/**
	 * Inorder traversal
	 */
	public String inOrder() {
		Node n = root;
		StringBuilder stringBuilder = new StringBuilder();
		inOrder(n, stringBuilder);
		return stringBuilder.toString();
	}

	private void inOrder(Node n, StringBuilder sb) {
		if (n == null) {
			return;
		} else {
			inOrder(n.left, sb);
			visit(n, sb);
			inOrder(n.right, sb);
		}
	}

	private void visit(Node n, StringBuilder sb) {
		while (n != null) {
			sb.append(n.data + " ");
			n = n.middle;
		}
	}

	private class Node {
		private T data;
		private Node left;
		private Node right;
		private Node middle;
		private Node parent;

		public Node(T data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}
}