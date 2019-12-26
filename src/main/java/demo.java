
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yousefahmed
 */
public class demo {
    
    
    public static int find(String [][]table,String state ,char alpha)
    {
        
        int ns=-1;
        for(int n=0;n<table.length;n++)
        {
            if(state.equals(table[n][0])&&alpha==table[n][1].charAt(0))
            {
                ns=n;
                break;
            }
            
            
        }
        
        
        
        
        return ns;
        
    }
    
    
    
    
    public static void main(String []args)
    {
        
        Scanner sc=new Scanner(System.in);
        
        System.out.println("The Number Of States: ");
        int NumOfStates=sc.nextInt();
        
        System.out.println("The Number Of Alphe");
        int NumOfAlpha=sc.nextInt();
        ArrayList<Character> Alpha=new ArrayList<Character>();
        for(int i=0;i<NumOfAlpha;i++)
        {
            System.out.println("The Alpha "+(i+1)+" : ");
            Alpha.add(sc.next().charAt(0));
        }
        
        //transiction table 
        int NumberOfTransaction=NumOfAlpha*NumOfStates;
        String TransactionTable[][]=new String[NumberOfTransaction][5];
        
        
        for(int i=0;i<NumberOfTransaction;i++)
        {
            
              System.out.print("Transition "+(i+1) + " : state------- alpha----newstate----update----move----- ");
            String State=sc.next();
            String alpha=sc.next();
            String newstate=sc.next();
            String update=sc.next();
            String move=sc.next();
            TransactionTable[i][0]=State;
            TransactionTable[i][1]=alpha;
            TransactionTable[i][2]=newstate;
            TransactionTable[i][3]=update;
            TransactionTable[i][4]=move;
            System.out.println(State+"   "+alpha+"    "+newstate+"    "+update+"    "+move);
        }
        
        //read tape
        ArrayList<Character>tape=new ArrayList<Character>();
        System.out.println("Enter The Tape");
        String temp=sc.next();
        for(int i=0;i<temp.length();i++)
        {
            tape.add(temp.charAt(i));
         //  System.out.println(temp.charAt(i));
        }
        System.out.println("Enter The Head state ");
        String StartState=sc.next();
        
        int CUP=0;
        String CurrentState=StartState;
        boolean Reject=false ,Accept=false ;
        while (true)
        {
            System.out.println("The Current State : "+CurrentState);
            int pos=find(TransactionTable,CurrentState,tape.get(CUP));
            if(pos==-1&&!CurrentState.equals("qaccpt")&&!CurrentState.equals("qrej"))
            {
                System.out.println("wrong input");
                break;
                
            }
            
            
            String NextState=TransactionTable[pos][2];
            char  UP=TransactionTable[pos][3].charAt(0);
            String Dir=TransactionTable[pos][4];
            
              tape.set(CUP, UP);
              System.out.println("CurrentState: "+CurrentState+" update : "+UP+" move : "+Dir);
            if(NextState.equals("qaccpt"))
            {
                Accept=true;
                break;
            }
            else if(NextState.equals("qrej"))
            {
                Reject=true;
                break;
            }
            
              if(CUP==0&&Dir.equals("l")){
                  CUP=0;
              }
              else if (CUP==tape.size()-1&&Dir.equals("r"))
              {
                  tape.add('>');
                  CUP++;
                  
              }
              
              else if (Dir.equals("r"))
              {
               CUP++;   
              }
              else if(Dir.equals("l"))
              {
                  CUP--;
              }
              
            
        CurrentState=NextState;    
        }
        
        
        if(Accept&&!Reject)
        {
            System.out.println("Accepted");
        }
        else 
        {
            System.err.println("Rejected");
        }
   
        
    }
    
}
