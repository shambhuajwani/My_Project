I have referenced the code through the link

http://www.codebytes.in/2014/10/red-black-tree-java-implementation.html

First I understood that then started it.


For integers First i created a class of Node 
with initialized key as -1 and  color as BLACK 


I have created it as menu driven Program.

so that user can perform insert, search, delete operations on it.

When we want to Insert node in RB tree then there are cases of insertion of node.

Do a simple BST Insertion and then perform recoloring and rotations on it.

insert method included done like BST insertion.

then fixtree() method have cases for insertion the newly inserted node is RED.
 
 Case 1: If uncle is right child of Grandparent
                and node is child of parent.
                Uncle and parent are both red.

Initilizion of node uncle and cretion of it has done in fixtree method.

Case 2 : If Uncle is Black then perform
                           left- left case,
                           right - right case,
                           left-right case,
                           right-left case,   
                
Now delete operation,

I have included a method called delete

which finds the desired node to be deleted.

It is a boolean function which returns true if it is deleted.

Else returns false.

The delete method finds its sucessor replaced by it and gets deleted.

The sucessor of little rotation is requied .
Therefore, I included transplant method.

Then we call for deletefixup method for deletion cases.
Case 1: occurs when node w, the sibling of node x, is red. 
                  Since w must have black children, we can switch the
                  colors of w and p[x] and then perform a left-rotation on 
                  p[x] without violating any of the red-black properties. 
                  The new sibling of x, which is one of w’s children prior to 
                  the rotation, is now black, and thus we have converted 
                  case 1 into case 2, and  3
                  
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
                    
                    
                    
 Case 3
                        x’s sibling w is black, w’s left child is red, and w’s right child is black
                        Case 3  occurs when w is black, its left child
                        is red, and its right child is black. We can switch the colors of w and its left
                        child left[w] and then perform a right rotation on w without violating any of the
                        red-black properties.                   


It also prints the tree by preorder traversal method.

Similary, for insertion , deletion of words.

I have just included String key.

and compareto()  java method for insertion 
to insert for left and right subtree.

and same deletion and printing of tree methods.

