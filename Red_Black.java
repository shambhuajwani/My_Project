/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red_black;
import java.util.Scanner;
/**
 *
 * @author shambhu
 */
public class Red_Black {

    /**
     * @param args the command line arguments
     */
    private final int RED = 0;
    private final int BLACK = 1;

    private class Node {

        int key = -1, color = BLACK;
        Node left = nil, right = nil, parent = nil;

        Node(int key) {
            this.key = key;
        } 
    }
    private final Node nil = new Node(-1); 
    private Node root = nil;
 
    private class Word_Node {

        String key = null;
        int color = BLACK;
        Word_Node left = nil_word, right = nil_word, parent = nil_word;

        Word_Node(String key) {
            this.key = key;
        } 
    }

    private final Word_Node nil_word = new Word_Node("x"); 
    private Word_Node word_root = nil_word;
    
    /* It prints the whole sentence of the added words 
       in color of Red and Black
    
    
    */ 
    
    
    
    
   
    
   
    

    
    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" Parent: "+node.parent.key+"\n");
        printTree(node.right);
    }

    private Node findNode(Node findNode, Node node) {
        if (root == nil) {
            return null;
        }

        if (findNode.key < node.key) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key > node.key) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }
    
    /* Do Simple Binary Search Tree Insertion
       Insert the new node as like Red Node, 
       and Fix it doing Rotations and Recoloring.                                 
     
    */

    private void insert(Node node) {
        Node temp = root;
        if (root == nil) {
            root = node;
            node.color = BLACK;
            node.parent = nil;
        } else {
            node.color = RED;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            // This metod it called to Maintain the Property of Red Black Tree
            fixTree(node);
        }
    }
    
     
    
    

    /* Fix tree is a method here in which it is defined
       as  
    
    */
    private void fixTree(Node node) {
        
        /*
        
        Case 1: If uncle is right child of Grandparent
                and node is child of parent.
                Uncle and parent are both red.
                        
        
        */
        
        // Parent color is Red
        while (node.parent.color == RED) {
            
            // Make a new Node is uncle
            Node uncle = nil;
            
            if (node.parent == node.parent.parent.left) {
                //insert it in right child of Grandparent
                uncle = node.parent.parent.right;

                /*Change the color of parent and grandparent
                
                */
                
                   
                if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                /*Case 2 : If Uncle is Black then perform
                           left- left case,
                           right - right case,
                           left-right case,
                           right-left case,   
                */
                
                if (node == node.parent.right) {
                   
                    node = node.parent;
                    rotateLeft(node);
                } 
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                 
                rotateRight(node.parent.parent);
            } else {
                
                // Similar Case 1 only uncle and parent positions are interchanged
                
                
                
                uncle = node.parent.parent.left;
                 if (uncle != nil && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                
                rotateLeft(node.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = nil;
            root = right;
        }
    }

    void rotateRight(Node node) {
        if (node.parent != nil) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = nil;
            root = left;
        }
    }

   /*
    Deletes the whole tree
     by just making the root null.
    
    */
    void deleteTree(){
        root = nil;
    }
    
    /*
      The transplant function adjusts the target node
      it performs the replacement of node to be deleted 
      by its sucessor
    */
    
   
    void transplant(Node target, Node with){ 
          if(target.parent == nil){
              root = with;
          }else if(target == target.parent.left){
              target.parent.left = with;
          }else
              target.parent.right = with;
          with.parent = target.parent;
    }
    
    /* The Below Function returns 
       the boolean value if the node is deleted.
       First it searches for the node to be deleted
       if found then the Function performs the normal
       BST Deletion . It finds the successor of the node
       replaces it and delete the node.
    */
    
    
    boolean delete(Node z){
        if((z = findNode(z, root))==null)return false;
        Node x;
        Node y = z; 
        int y_original_color = y.color;
        
        if(z.left == nil){
            x = z.right;  
            transplant(z, z.right);  
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left); 
        }else{
            y = treeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color; 
        }
        if(y_original_color==BLACK)
            deleteFixup(x);  
        return true;
    }
    
    void deleteFixup(Node x){
        while(x!=root && x.color == BLACK){ 
            if(x == x.parent.left){
                
                // Case 1: x's sibling w is red
                
                Node w = x.parent.right;
                /*
                  Case 1: occurs when node w, the sibling of node x, is red. 
                  Since w must have black children, we can switch the
                  colors of w and p[x] and then perform a left-rotation on 
                  p[x] without violating any of the red-black properties. 
                  The new sibling of x, which is one of w’s children prior to 
                  the rotation, is now black, and thus we have converted 
                  case 1 into case 2, and  3
                */
                
                if(w.color == RED){
                    w.color = BLACK;     //CASE 1
                    x.parent.color = RED;//
                    rotateLeft(x.parent);//
                    w = x.parent.right;//
                }
                
                
                if(w.left.color == BLACK && w.right.color == BLACK){
                    /*
                    Case 2
                    x’s sibling w is black, and both of w’s children are black
                    both of w’s children are black. 
                    Since w is also black, we take one black off both x and w,
                    leaving x with only one black and leaving w red. 
                    To compensate for removing one black from x and w, we would 
                    like to add an extra black to p[x], which was originally either 
                    red or black. We do so by repeating the while loop with parent[x] as
                    the new node x. Observe that if we enter case 2 through case 1, 
                    the new node x is red-and-black, since the original parent[x] was red. 
                    Hence, the value c of the color attribute of the new node x is RED, 
                    and the loop terminates when it tests the loop
                    condition.
                    */
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    /*
                       Case 3
                        x’s sibling w is black, w’s left child is red, and w’s right child is black
                        Case 3  occurs when w is black, its left child
                        is red, and its right child is black. We can switch the colors of w and its left
                        child left[w] and then perform a right rotation on w without violating any of the
                        red-black properties.

                    */
                    
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }   
                
               
               /* Case 1
                
                */
                 
                
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                Node w = x.parent.left;
                if(w.color == RED){
                    
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                
             
                
                
                if(w.right.color == BLACK && w.left.color == BLACK){
                    /*
                    Case 2
                    */

                    
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    /*
                       Case 3
                    */
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
                
                
                
            }
        }
        x.color = BLACK; 
    }
    
    
    /*
      This method finds the left subtree of node
      to be deleted.
    */
    
    Node treeMinimum(Node subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    
     public void printTree_Sentence(Word_Node node) {
        
        //If the word is not present then it is nill
         
        if (node == nil_word) {
            return;
        }
        
        //It does The preorder traversal of the words
        
        printTree_Sentence(node.left);
        System.out.print(((node.color==RED)?"Color: Red ":"Color: Black ")+"Key: "+node.key+" Parent: "+node.parent.key+"\n");
       System.out.println("   "+ node.key + "  "+ node.color);
        printTree_Sentence(node.right);
    }

     /* The below method finds the searched word
        It do a Binary search of the String by comparing two strings    
        values.
     */
    
    
    
    
     private Word_Node findNode_Word(Word_Node findNode, Word_Node node) {
        if (word_root == nil_word) {
            return null;
        }
        
        
        
        if (findNode.key.compareTo(node.key)<0) {
            if (node.left != nil_word) {
                return findNode_Word(findNode, node.left);
            }
        } else if (findNode.key.compareTo(node.key)>0) {
            if (node.right != nil_word) {
                return findNode_Word(findNode, node.right);
            }
        } else if (findNode.key.equals(node.key)) {
            return node;
        }
        return null;
    }
     
     // Insertion of word

    private void insert_Word(Word_Node node) {
        Word_Node temp = word_root;
        if (word_root == nil_word) {
            word_root = node;
            node.color = BLACK;
            node.parent = nil_word;
        } else {
            node.color = RED;
            while (true) {
                if (node.key.compareTo(temp.key)<0) {
                    if (temp.left == nil_word) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key.compareTo(temp.key)>0) {
                    if (temp.right == nil_word) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree_Word(node);
        }
    }

    //Takes as argument the newly inserted node
    
    // Maintains the Property Of red black Tree
    
    
    private void fixTree_Word(Word_Node node) {
        while (node.parent.color == RED) {
            Word_Node uncle = nil_word;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;

                if (uncle != nil_word && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                } 
                if (node == node.parent.right) {
                   
                    node = node.parent;
                    rotateLeft_Word(node);
                } 
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                 
                rotateRight_Word(node.parent.parent);
            } else {
                uncle = node.parent.parent.left;
                 if (uncle != nil_word && uncle.color == RED) {
                    node.parent.color = BLACK;
                    uncle.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    
                    node = node.parent;
                    rotateRight_Word(node);
                }
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                
                rotateLeft_Word(node.parent.parent);
            }
        }
        word_root.color = BLACK;
    }

    // Left Rotation
    
    
    void rotateLeft_Word(Word_Node node) {
        if (node.parent != nil_word) {
            if (node == node.parent.left) {
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != nil_word) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {
            Word_Node right = word_root.right;
            word_root.right = right.left;
            right.left.parent = word_root;
            word_root.parent = right;
            right.left = word_root;
            right.parent = nil_word;
            word_root = right;
        }
    }
    
    //Right Rotation

    void rotateRight_Word(Word_Node node) {
        if (node.parent != nil_word) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != nil_word) {
                node.left.right.parent = node;
            }
            node.left = node.left.right;
            node.parent.right = node;
        } else {
            Word_Node left = word_root.left;
            word_root.left = word_root.left.right;
            left.right.parent = word_root;
            word_root.parent = left;
            left.right = word_root;
            left.parent = nil_word;
            word_root = left;
        }
    }

   
    // Deleting the whole Sentence
    void deleteTree_Word(){
        word_root = nil_word;
    }
    
    //Transplant 
    
    
    void transplant_Word(Word_Node target, Word_Node with){ 
          if(target.parent == null){
              word_root = with;
          }else if(target.equals(target.parent.left)){
              target.parent.left = with;
          }else
              target.parent.right = with;
          with.parent = target.parent;
    }
    
    // Deletion with similar cases like deletion of integers
    
    boolean delete_Word(Word_Node z){
        if((z = findNode_Word(z, word_root))==null)return false;
        Word_Node x;
        Word_Node y = z; 
        int y_original_color = y.color;
        
        if(z.left == nil_word){
            x = z.right;  
            transplant_Word(z, z.right);  
        }else if(z.right == nil_word){
            x = z.left;
            transplant_Word(z, z.left); 
        }else{
            y = treeMinimum_Word(z.right);
            y_original_color = y.color;
            x = y.right;
            if(y.parent.key.equalsIgnoreCase(z.key))
                x.parent = y;
            else{
                transplant_Word(y, y.right);
                y.right.key = z.right.key;
                y.right.parent.key = y.key;
            }
            transplant_Word(z, y);
            y.left.key = z.left.key;
            y.left.parent.key = y.key;
            y.color = z.color; 
        }
        if(y_original_color==BLACK)
            deleteFixup_Word(x);  
        return true;
    }
    
    // Maintains the property
    
    void deleteFixup_Word(Word_Node x){
        while(!x.equals(word_root) && x.color==BLACK){ 
            if(x.equals(x.parent.left)){
                Word_Node w = x.parent.right;
                if(w.color==RED){
                    w.color = BLACK;     //CASE 1
                    x.parent.color = RED;//
                    rotateLeft_Word(x.parent);//
                    w = x.parent.right;//
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;//CASE 2
                    x = x.parent;// CASE 2
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;//CASE 3
                    w.color = RED;//CASE 3
                    rotateRight_Word(w);//CASE 3
                    w = x.parent.right;// CASE 3
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft_Word(x.parent);
                    x = word_root;
                }
            }else{
                Word_Node w = x.parent.left;
                if(w.color==RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight_Word(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft_Word(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight_Word(x.parent);
                    x = word_root;
                }
            }
        }
        x.color = BLACK; 
    }
    
    Word_Node treeMinimum_Word(Word_Node subTreeRoot){
        while(subTreeRoot.left!=nil_word){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    
    
    
    public void Menu() {
        Scanner scan = new Scanner(System.in);
        
        char ch = 'n';
         String word_item;
         int item;
            Node node;
            Word_Node word_node;
           
        do{
        
        System.out.println("1.- Insert element\n");
        System.out.println("2.- Delete element\n");
        System.out.println("3.- Check element\n");
        System.out.println("4.- Print tree\n");
        System.out.println("5.- Delete tree\n");
        
        System.out.println("Words or String Operations\n");
        
        System.out.println("6.- Enter Word\n");
        System.out.println("7.- Delete Word\n");
        System.out.println("8.- Search Word\n");
        System.out.println("9.- Print Sentence\n");
        System.out.println("10.- Delete Sentence\n");
        System.out.println("12.exit\n");
        
        System.out.println("Enter your choice\n");
        
        
            int choice = scan.nextInt();

           
           
            
            switch (choice) {
                case 1: System.out.println("Enter the element to Insert");
                        item = scan.nextInt();
                        node = new Node(item);
                        insert(node);
                        printTree(root);
                        break;
                case 2: System.out.println("Enter the element to delete\n");
                    item = scan.nextInt();
                    
                    
                    
                        node = new Node(item);
                        System.out.print("\nDeleting item " + item);
                        
                        if (delete(node)) 
                        {
                            System.out.print(": deleted!");
                        } 
                        
                        else 
                        {
                            System.out.print(": does not exist!");
                        }
                       
                    
                    
                    System.out.println();
                    
                    printTree(root);
                    
                    break;
                
                case 3: System.out.println("Enter the element to search\n");
                        item = scan.nextInt();
                    
                        
                          node = new Node(item);
                          System.out.println((findNode(node, root) != null) ? "found" : "not found");
                          //item = scan.nextInt();
                        
                        break;
                
                case 4:
                       printTree(root);
                       break;
                
                case 5:
                       deleteTree();
                       System.out.println("Tree deleted!");
                       break;
                    
               case 6:  System.out.println("Enter word to Insert");
                        word_item = scan.next();
                        word_node = new Word_Node(word_item);
                        insert_Word(word_node);
                        printTree_Sentence(word_root);
                        break; 
                        
               case 7: System.out.println("Enter word to delete\n");
                        word_item = scan.next();
                    
                    
                    
                        word_node = new Word_Node(word_item);
                        System.out.print("\nDeleting item " + word_item);
                        
                        if (delete_Word(word_node)) 
                        {
                            System.out.print(": deleted!");
                        } 
                        
                        else 
                        {
                            System.out.print(": does not exist!");
                        }
                       
                    
                    
                    System.out.println();
                    
                    printTree_Sentence(word_root);
                    break;
               
               case 8: System.out.println("Enter the word to search\n");
                        word_item = scan.next();
                    
                        
                          word_node = new Word_Node(word_item);
                          System.out.println((findNode_Word(word_node, word_root) != null) ? "found" : "not found");
                          //item = scan.nextInt();
                        
                        break;
                   
               case 9:printTree_Sentence(word_root);
                       break;
                   
               case 10: deleteTree_Word();
                        System.out.println("sentence deleted!");
                        break;
               
               case 11: break;         
               
               default: System.out.println("Wrong choice Try Again!!!\n");
               
                    
            }
           System.out.println("Enter your y or Y to continue\n");
           ch = scan.next().charAt(0);
        }while(ch=='y'||ch=='Y');
    }
    
   public static void main(String[] args) {
        // TODO code application logic here
        Red_Black rbt = new Red_Black();
        rbt.Menu();
        
        
    }
    
}
