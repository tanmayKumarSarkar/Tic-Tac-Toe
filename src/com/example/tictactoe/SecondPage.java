package com.example.tictactoe;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



public class SecondPage extends Activity {

	
	
    int i, j, k = 0,winAi=0,winH=0,playCount=1;
    Button b[][];
    TextView textView,textView2,textView3,textView4;
    AlertDialog.Builder prmptMsg;
    //SharedPreferences sPfAi,sPfH,sPfP;
    SharedPreferences savedData;
    int c[][];
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_page);
		//Toast.makeText(getApplicationContext(),"First Your Move !!", Toast.LENGTH_LONG).show();
		
		savedData = getSharedPreferences("myData", Context.MODE_PRIVATE);
		setBoard();
	}

	public void btnClick(View v){
		
		v.setBackgroundResource(R.drawable.o);
		v.setTag("o");
		v.setClickable(false);
		v.setEnabled(false);
		
		for(i=1;i<=3;i++){
			for(j=1;j<=3;j++){
				if(b[i][j]==v){
					c[i][j]=0;						//Human=0
					
				}
			}
		}
		//checkBoard();
		if(!checkBoard()){
			playAi();
		}
		
		
		
		//Toast.makeText(getApplicationContext(), v.getTag().toString(), Toast.LENGTH_SHORT).show();
		
	}
	
	public void setBoard(){
		
		/*sPfAi = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		winAi=sPfAi.getInt("winAi", 0);
		sPfH = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		winH=sPfH.getInt("winH", 0);
		sPfP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		playCount=sPfP.getInt("playCount", 0);*/
		
		
		
		if(savedData.contains("winAi")){winAi=savedData.getInt("winAi", winAi);}
		else{winAi=0;}
		
		if(savedData.contains("winH")){winH=savedData.getInt("winH", winH);}
		else{winH=0;}
		
		if(savedData.contains("playCount")){playCount=savedData.getInt("playCount", playCount);}
		else{playCount=1;}
		
		b = new Button[4][4];
        c = new int[4][4];
        textView = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        
        prmptMsg = new AlertDialog.Builder(this);
        prmptMsg.setTitle("Game Over !!!!");
        prmptMsg.setCancelable(false);
        prmptMsg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface d1, int id1) {
				startActivity(getIntent());
				finish();
			}
		});
        prmptMsg.setNegativeButton("No", new DialogInterface.OnClickListener() {
;            
			@Override
			public void onClick(DialogInterface d2, int id2) {
				
				Editor savedDataEditorD = savedData.edit();
				savedDataEditorD.clear();
				savedDataEditorD.commit();
				
				Intent intent =new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
  
        
        int drw=playCount-winH+winAi-1;
        textView2.setText(winAi+"");
        textView3.setText(winH+"");
        textView4.setText(drw+"");
		b[1][1]=(Button)findViewById(R.id.btn11);
		b[1][2]=(Button)findViewById(R.id.btn12);
		b[1][3]=(Button)findViewById(R.id.btn13);
		
		b[2][1]=(Button)findViewById(R.id.btn21);
		b[2][2]=(Button)findViewById(R.id.btn22);
		b[2][3]=(Button)findViewById(R.id.btn23);
		
		b[3][1]=(Button)findViewById(R.id.btn31);
		b[3][2]=(Button)findViewById(R.id.btn32);
		b[3][3]=(Button)findViewById(R.id.btn33);
		
		 for (i = 0; i <= 3; i++) {
             for (j = 0; j <= 3; j++)
                  c[i][j] = 2;						  //Default / Blank=2
        }
		 
		 if(playCount%2==0){
	        	textView.setBackgroundResource(R.drawable.aigoes);
	        	
	        		playAi();
	    		
	        }else{
	        	textView.setBackgroundResource(R.drawable.firstyour);
	        }
	}
	

	public void playAi(){
		
		
		//win getting third box------------------>>>>>
		
		if(c[1][1]==2 &&
                ((c[1][2]==1 && c[1][3]==1) ||
                 (c[2][2]==1 && c[3][3]==1) ||
                 (c[2][1]==1 && c[3][1]==1))) {
           markSquare(1,1);
      } else if (c[1][2]==2 &&
                ((c[2][2]==1 && c[3][2]==1) ||
                 (c[1][1]==1 && c[1][3]==1))) {
           markSquare(1,2);
      } else if(c[1][3]==2 &&
                ((c[1][1]==1 && c[1][2]==1) ||
                 (c[3][1]==1 && c[2][2]==1) ||
                 (c[2][3]==1 && c[3][3]==1))) {
           markSquare(1,3);
      } else if(c[2][1]==2 &&
                ((c[2][2]==1 && c[2][3]==1) ||
                 (c[1][1]==1 && c[3][1]==1))){
           markSquare(2,1);
      } else if(c[2][2]==2 &&
                ((c[1][1]==1 && c[3][3]==1) ||
                 (c[1][2]==1 && c[3][2]==1) ||
                 (c[3][1]==1 && c[1][3]==1) ||
                 (c[2][1]==1 && c[2][3]==1))) {
           markSquare(2,2);
      } else if(c[2][3]==2 &&
                ((c[2][1]==1 && c[2][2]==1) ||
                 (c[1][3]==1 && c[3][3]==1))) {
           markSquare(2,3);
      } else if(c[3][1]==2 &&
                ((c[1][1]==1 && c[2][1]==1) ||
                 (c[3][2]==1 && c[3][3]==1) ||
                 (c[2][2]==1 && c[1][3]==1))){
           markSquare(3,1);
      } else if(c[3][2]==2 &&
                ((c[1][2]==1 && c[2][2]==1) ||
                 (c[3][1]==1 && c[3][3]==1))) {
           markSquare(3,2);
      }else if( c[3][3]==2 &&
                ((c[1][1]==1 && c[2][2]==1) ||
                 (c[1][3]==1 && c[2][3]==1) ||
                 (c[3][1]==1 && c[3][2]==1))) {
           markSquare(3,3);
		
		//block if two move is done--------------->>>>>
		
      }else if(c[1][1]==2 &&
                 ((c[1][2]==0 && c[1][3]==0) ||
                  (c[2][2]==0 && c[3][3]==0) ||
                  (c[2][1]==0 && c[3][1]==0))) {
            markSquare(1,1);
       } else if (c[1][2]==2 &&
                 ((c[2][2]==0 && c[3][2]==0) ||
                  (c[1][1]==0 && c[1][3]==0))) {
            markSquare(1,2);
       } else if(c[1][3]==2 &&
                 ((c[1][1]==0 && c[1][2]==0) ||
                  (c[3][1]==0 && c[2][2]==0) ||
                  (c[2][3]==0 && c[3][3]==0))) {
            markSquare(1,3);
       } else if(c[2][1]==2 &&
                 ((c[2][2]==0 && c[2][3]==0) ||
                  (c[1][1]==0 && c[3][1]==0))){
            markSquare(2,1);
       } else if(c[2][2]==2 &&
                 ((c[1][1]==0 && c[3][3]==0) ||
                  (c[1][2]==0 && c[3][2]==0) ||
                  (c[3][1]==0 && c[1][3]==0) ||
                  (c[2][1]==0 && c[2][3]==0))) {
            markSquare(2,2);
       } else if(c[2][3]==2 &&
                 ((c[2][1]==0 && c[2][2]==0) ||
                  (c[1][3]==0 && c[3][3]==0))) {
            markSquare(2,3);
       } else if(c[3][1]==2 &&
                 ((c[1][1]==0 && c[2][1]==0) ||
                  (c[3][2]==0 && c[3][3]==0) ||
                  (c[2][2]==0 && c[1][3]==0))){
            markSquare(3,1);
       } else if(c[3][2]==2 &&
                 ((c[1][2]==0 && c[2][2]==0) ||
                  (c[3][1]==0 && c[3][3]==0))) {
            markSquare(3,2);
       }else if( c[3][3]==2 &&
                 ((c[1][1]==0 && c[2][2]==0) ||
                  (c[1][3]==0 && c[2][3]==0) ||
                  (c[3][1]==0 && c[3][2]==0))) {
            markSquare(3,3);
       } else {
            Random rand = new Random();
            
            int a = rand.nextInt(4);
            int b = rand.nextInt(4);
            while(a==0 || b==0 || c[a][b]!=2) {
                 a = rand.nextInt(4);
                 b = rand.nextInt(4);
            }
            markSquare(a,b);
       }
  }


       private void markSquare(int x, int y) {
    	   
    	    b[x][y].setBackgroundResource(R.drawable.x);
    	    b[x][y].setEnabled(false);
    	    b[x][y].setClickable(false);
    	    b[x][y].setTag("x");
            c[x][y] = 1;								//AI=1
            checkBoard();
       }

	public boolean checkBoard() {
		
		Editor savedDataEditor = savedData.edit();
		
		boolean gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                  || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                  || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                  || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                  || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                  || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                  || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                  || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
        	 
        	textView.setBackgroundResource(R.drawable.gouwin);
        	prmptMsg.setTitle("Game over. You win!");
        	prmptMsg.setMessage("Want To continue !!");
        	//prmptMsg.create();
        	prmptMsg.show();
        	
        	winH++;
        	playCount++;
        	
        	savedDataEditor.putInt("winAi", winAi);
        	savedDataEditor.putInt("winH", winH);
        	savedDataEditor.putInt("playCount", playCount);
        	savedDataEditor.commit();
        	
        	
        	/*sPfH = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorH = sPfH.edit();
            editorH.putInt("winH", winH);
            editorH.commit();
            
            sPfAi = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorAi = sPfAi.edit();
            editorAi.putInt("winAi", winAi);
            editorAi.commit();
            
            sPfP = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorP = sPfP.edit();
            editorP.putInt("playCount", playCount);
            editorP.commit();*/
        	/*new Thread(new Runnable(){
        		public void run(){
        			
        			Toast.makeText(getApplicationContext(),"Game over. You win!", Toast.LENGTH_LONG).show();
        		}
        	});*/ 
        	
             gameOver = true;
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                  || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                  || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                  || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                  || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                  || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                  || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                  || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
        	
        	textView.setBackgroundResource(R.drawable.goulost);
        	prmptMsg.setTitle("Game over. You lost!");
        	prmptMsg.setMessage("Want To continue !!");
        	//prmptMsg.create();
        	prmptMsg.show();
        	
        	winAi++;
        	playCount++;
        	
        	savedDataEditor.putInt("winAi", winAi);
        	savedDataEditor.putInt("winH", winH);
        	savedDataEditor.putInt("playCount", playCount);
        	savedDataEditor.commit();
        	
        	/*sPfH = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorH = sPfH.edit();
            editorH.putInt("winH", winH);
            editorH.commit();
            
            sPfAi = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorAi = sPfAi.edit();
            editorAi.putInt("winAi", winAi);
            editorAi.commit();
            
            sPfP = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorP = sPfP.edit();
            editorP.putInt("playCount", playCount);
            editorP.commit();*/
        	/*new Thread(new Runnable(){
        		public void run(){
        			
        			Toast.makeText(getApplicationContext(),"Game over. You lost!", Toast.LENGTH_LONG).show();
        		}
        	});*/
             gameOver = true;
        } else {
             boolean empty = false;
             for(i=1; i<=3; i++) {
                  for(j=1; j<=3; j++) {
                       if(c[i][j]==2) {
                            empty = true;
                            break;
                       }
                  }
             }
             if(!empty) {
                  gameOver = true;
                  
                  textView.setBackgroundResource(R.drawable.godraw);
                  prmptMsg.setTitle("Game over. It's a draw!");
              	prmptMsg.setMessage("Want To continue !!");
              	//prmptMsg.create();
            	prmptMsg.show();
              	
              	playCount++;
              	
              	savedDataEditor.putInt("winAi", winAi);
            	savedDataEditor.putInt("winH", winH);
            	savedDataEditor.putInt("playCount", playCount);
            	savedDataEditor.commit();
              	
              	/*sPfH = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editorH = sPfH.edit();
                editorH.putInt("winH", winH);
                editorH.commit();
                
                sPfAi = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editorAi = sPfAi.edit();
                editorAi.putInt("winAi", winAi);
                editorAi.commit();
                
                sPfP = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editorP = sPfP.edit();
                editorP.putInt("playCount", playCount);
                editorP.commit();*/
                  //Toast.makeText(getApplicationContext(),"Game over. It's a draw!", Toast.LENGTH_LONG).show();
             }
        }
        return gameOver;
   
	}
	

}
