package com.showyourwork.engine;

public class BinaryTree<T>
{
    public BinaryTree( )
    {
        root = null;
    }

    public BinaryTree( T t )
    {
        root = new BinaryNode<T>( t, null, null );
    }
    public BinaryTree( T t, T leftItem, T rightItem )
    {
       // root = new BinaryNode( rootItem, leftItem, rightItem );
    }    

    public void printPreOrder( )
    {
        if( root != null )
            root.printPreOrder( );
    }

    public void printInOrder( )
    {
        if( root != null )
           root.printInOrder( );
    }

    public void printPostOrder( )
    {
        if( root != null )
           root.printPostOrder( );
    }

    public void makeEmpty( )
    {
        root = null;
    }

    public boolean isEmpty( )
    {
        return root == null;
    }
    
    /**
     * Merge routine for BinaryTree class.
     * Forms a new tree from rootItem, t1 and t2.
     * Does not allow t1 and t2 to be the same.
     * Correctly handles other aliasing conditions.
     */
    public void merge( T t, BinaryTree<T> t1, BinaryTree<T> t2 )
    {
        if( t1.root == t2.root && t1.root != null )
        {
            System.err.println( "leftTree==rightTree; merge aborted" );
            return;
        }

            // Allocate new node
        root = new BinaryNode<T>( t, t1.root, t2.root );

            // Ensure that every node is in one tree
        if( this != t1 )
            t1.root = null;
        if( this != t2 )
            t2.root = null;
    }

    public int size( )
    {
        return BinaryNode.size( root );
    }

    public int height( )
    {
        return BinaryNode.height( root );
    }

    public BinaryNode<T> getRoot( )
    {
        return root;
    }
    
    private BinaryNode<T> root;

    
}