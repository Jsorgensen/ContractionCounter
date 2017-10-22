package com.Jsorgensen.CC;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import java.io.*;
import java.util.*;
import android.widget.Toolbar.*;
import android.util.*;
import android.graphics.*;
import android.view.*;
import android.text.format.*;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import android.widget.TextView.*;
import java.util.Arrays;
import java.util.ArrayList;
import android.app.backup.*;
import android.content.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.widget.Toast;
import android.printservice.*;


public class MainActivity extends Activity implements OnClickListener, OnFocusChangeListener
{
	
	Bundle savedInstanceState;
	RelativeLayout rl;
	ScrollView sv;
	List<RelativeLayout.LayoutParams> rlp = new ArrayList<RelativeLayout.LayoutParams>();
	List<EditText> et = new ArrayList<EditText>();
	List<TextView> tv = new ArrayList<TextView>();
	List<TextView> tvb = new ArrayList<TextView>();
	List<Button> bt = new ArrayList<Button>();
	List<Long> tm = new ArrayList<Long>();
	List<EditText> etb = new ArrayList<EditText>();
	int i=-1, j=-1, k=-1;
	String filename = "temp.txt";
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
		sv = new ScrollView(this);
		sv.setId(300);
		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		sv.setLayoutParams(rlp.get(i));
		
		rl = new RelativeLayout(this);
		rl.setId(301);
		rlp.add(new RelativeLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rl.setLayoutParams(rlp.get(i));
		rl.setGravity(Gravity.START);
		rlp.get(0).addRule(RelativeLayout.BELOW, rl.getId());
		
		et.add(new EditText(this));
		et.get(0).setLayoutParams(rlp.get(i));
		et.get(0).setHint("Enter Time");
		et.get(0).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		et.get(0).setTextColor(Color.BLACK);
		//et.get(0).setBackgroundColor(Color.BLUE);
		et.get(0).setId(1000);
		et.get(0).setOnFocusChangeListener(this);
		rl.addView(et.get(0));
		
		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
		rlp.get(i).addRule(RelativeLayout.ALIGN_TOP, rl.getId());
		bt.add(new Button(this));
		bt.get(0).setLayoutParams(rlp.get(i));
		bt.get(0).setText("START");
		bt.get(0).setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
		bt.get(0).setTextColor(Color.GREEN);
		bt.get(0).setId(2000);
		bt.get(0).setOnClickListener(this);
		rl.addView(bt.get(0)); 

		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
		rlp.get(i).addRule(RelativeLayout.BELOW, bt.get(0).getId());
		bt.add(new Button(this));
		bt.get(1).setLayoutParams(rlp.get(i));
		bt.get(1).setText("Save");
		bt.get(1).setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		bt.get(1).setTextColor(Color.RED);
		bt.get(1).setBackgroundColor(Color.GREEN);
		bt.get(1).setId(2001);
		bt.get(1).setOnClickListener(this);
		rl.addView(bt.get(1)); 

		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
		rlp.get(i).addRule(RelativeLayout.BELOW, bt.get(1).getId());
		etb.add(new EditText(this));
		etb.get(0).setLayoutParams(rlp.get(i));
		etb.get(0).setHint("File Name:");
		etb.get(0).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		etb.get(0).setTextColor(Color.BLACK);
		//etb.get(0).setBackgroundColor(Color.BLUE);
		etb.get(0).setId(2500);
		etb.get(0).setOnFocusChangeListener(this);
		rl.addView(etb.get(0));

		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
		rlp.get(i).addRule(RelativeLayout.BELOW, etb.get(0).getId());
		bt.add(new Button(this));
		bt.get(2).setLayoutParams(rlp.get(i));
		bt.get(2).setText("List Files");
		bt.get(2).setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		bt.get(2).setTextColor(Color.RED);
		bt.get(2).setBackgroundColor(Color.GREEN);
		bt.get(2).setId(2002);
		bt.get(2).setOnClickListener(this);
		rl.addView(bt.get(2)); 
		
		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
		rlp.get(i).addRule(RelativeLayout.BELOW, bt.get(2).getId());
		bt.add(new Button(this));
		bt.get(3).setLayoutParams(rlp.get(i));
		bt.get(3).setText("Erase File");
		bt.get(3).setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		bt.get(3).setTextColor(Color.RED);
		bt.get(3).setBackgroundColor(Color.GREEN);
		bt.get(3).setId(2003);
		bt.get(3).setOnClickListener(this);
		rl.addView(bt.get(3)); 
		
		sv.addView(rl);
		setContentView(sv);

		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
		rlp.get(i).addRule(RelativeLayout.BELOW, bt.get(3).getId());
		for(int itv=0; itv<=1; itv++){
			tvb.add(new TextView(this));
			tvb.get(itv).setLayoutParams(rlp.get(i));
			tvb.get(itv).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			tvb.get(itv).setTextColor(Color.GREEN);
			tvb.get(itv).setBackgroundColor(Color.BLACK);
			tvb.get(itv).setId(2900+itv);
			tvb.get(itv).setText("   tm="+tm.toString()+"   ");
			tvb.get(itv).setFreezesText(true);
			rl.addView(tvb.get(itv));
			rlp.add(new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			i++;
			rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
			rlp.get(i).addRule(RelativeLayout.BELOW, tvb.get(itv).getId());
		}
		
		rlp.add(new RelativeLayout.LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		i++;
		rlp.get(i).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, rl.getId());
		rlp.get(i).addRule(RelativeLayout.BELOW, tvb.get(1).getId());
		bt.add(new Button(this));
		bt.get(4).setLayoutParams(rlp.get(i));
		bt.get(4).setText("Test Save");
		bt.get(4).setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		bt.get(4).setTextColor(Color.RED);
		bt.get(4).setBackgroundColor(Color.GREEN);
		bt.get(4).setId(2004);
		bt.get(4).setOnClickListener(this);
		rl.addView(bt.get(4)); 
		
		try {
			FileInputStream fileIn = openFileInput(filename);
			InputStreamReader InputRead = new InputStreamReader(fileIn);

			int READ_BLOCK_SIZE = 1000;
			char[] inputBuffer= new char[READ_BLOCK_SIZE];
			String s="";
			int charRead;

			String translate = Integer.toString(InputRead.read(inputBuffer));
			while ((charRead=InputRead.read(inputBuffer))>0) {
				// char to string conversion
				String readstring=String.copyValueOf(inputBuffer,0,charRead);
				s +=readstring;					
			}
			tvb.get(0).setText(s);
			InputRead.close();
			Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();
			s = s.replaceAll("[\\[\\] ]", "");
			String[] inp = s.split(",");
			Long[] conv = new Long[inp.length];
			for(int id=0; id<conv.length; id++){
				conv[id] = Long.valueOf(inp[id]);
			}
			tm = new ArrayList(Arrays.asList(conv));
			tvb.get(1).setText(s+",  tm size="+Integer.toString(tm.size()));
			
			if(tm.size()>0){
				k++;
				addtime(tm.get(0), tm.get(0));
			}
			for(int ic=1; ic<tm.size(); ic++){
				k++;
				addtime(tm.get(ic), tm.get(ic-1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			tvb.get(0).setText("file not found");
		}
    }
	
	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		if(etb.get(0).getText().toString().equals("")){
			etb.get(0).setText("temp");
		}
		filename = etb.get(0).getText().toString()+".txt";
		switch(v.getId()){
			case(2000):
				// add current time
				tm.add(System.currentTimeMillis()); k++;
				tm.set(k, tm.get(k) - TimeUnit.HOURS.toMillis(6));
				
				if(k<1){
					addtime(tm.get(k), tm.get(k));
				}
				else{
					addtime(tm.get(k), tm.get(k-1));
				}
				  break;
				  
			case(2001):
				// add-write text into file
				save();
				break;
				
			case(2002):
				// list files
				String path = MainActivity.this.getFilesDir().getAbsolutePath()+"/";  
				//path = Environment.getRootDirectory().toString();
				Log.d("Files", "Path: " + path);
				File f = new File(path);        
				File fileb[] = f.listFiles();
				Log.d("Files", "Size: "+ fileb.length);
				String disp = "";
				for (int i=0; i < fileb.length; i++)
				{
					Log.d("Files", "FileName:" + fileb[i].getName());
					disp += fileb[i].getName()+"\n";
				}
				Toast.makeText(getBaseContext(), disp, Toast.LENGTH_LONG).show();
				disp = MainActivity.this.getFilesDir().getAbsolutePath();
				tvb.get(1).setText(disp);
				break;
				
			case(2003):
				// delete file and remove current
				try {
					File dir = getFilesDir();
					File file = new File(dir, filename);
					boolean deleted = file.delete();
					tvb.get(0).setText("File Deleted");
				} catch(Exception e){
					tvb.get(0).setText("File Not Deleted");
				}
				
				for(int ie=tm.size()-1; ie>0; ie--){
					k = -1; j = -1;
					rl.removeView(et.get(ie));
					rl.removeView(tv.get(ie-1));
					et.remove(ie);
					tv.remove(ie-1);
					et.get(0).setText("");
					tm.clear();
				}
			case(2004):
				stuff();
				break;
		}
	}
	
	@Override
	public void save(){
		try {
			FileOutputStream fileout=openFileOutput(filename, MODE_WORLD_READABLE);
			OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
			outputWriter.write(tm.toString());
			outputWriter.close();

			//display file saved message
			Toast.makeText(getBaseContext(), "File saved successfully!",
						   Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void addtime(Long tml, Long tml0){

		Long millis;
		String tme;
		
		if(j>-1){
			rlp.add(new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			i++;
			rlp.get(i).addRule(RelativeLayout.BELOW, et.get(j).getId());
			et.add(new EditText(this)); j++;
			et.get(j).setLayoutParams(rlp.get(i));
			et.get(j).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
			et.get(j).setTextColor(Color.BLUE);
			//et.get(j).setBackgroundColor(Color.BLUE);
			et.get(j).setId(1000+j);
			et.get(j).setOnFocusChangeListener(this);
			rl.addView(et.get(j));

			millis = tml - tml0;
			tme = String.format("%02d:%02d", 
								TimeUnit.MILLISECONDS.toMinutes(millis) - 
								TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
								TimeUnit.MILLISECONDS.toSeconds(millis) - 
								TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

			rlp.add(new RelativeLayout.LayoutParams(
						ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			i++;
			rlp.get(i).addRule(RelativeLayout.ALIGN_BOTTOM, et.get(j-1).getId());
			rlp.get(i).addRule(RelativeLayout.RIGHT_OF, et.get(j-1).getId());
			tv.add(new TextView(this));
			tv.get(j-1).setLayoutParams(rlp.get(i));
			tv.get(j-1).setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
			tv.get(j-1).setTextColor(Color.RED);
			tv.get(j-1).setBackgroundColor(Color.BLUE);
			tv.get(j-1).setId(3000+j-1);
			rl.addView(tv.get(j-1));

			for(int j2=j; j2>0; j2--){
				et.get(j2).setText(et.get(j2-1).getText().toString());
			}
			for(int j2=j-1; j2>0; j2--){
				tv.get(j2).setText(tv.get(j2-1).getText().toString());
			}
			tv.get(0).setText(tme);
		}
		else{j++;}
		millis = tml;
		tme = String.format("%02d:%02d:%02d", 
							TimeUnit.MILLISECONDS.toHours(millis) - 
							TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)),
							TimeUnit.MILLISECONDS.toMinutes(millis) - 
							TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
							TimeUnit.MILLISECONDS.toSeconds(millis) - 
							TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

		et.get(0).setText(tme);
	}

	@Override
	public void onFocusChange(View v, boolean p2)
	{
		int ref = v.getId()-1000;
		switch(ref){
			
			case(1500):
				if(etb.get(0).getText().toString().equals("")){
					etb.get(0).setText("temp");
				}
				break;
				
			default:
				int id = k - ref;
				String tme;
				Long millis;

				if(id>0||ref>0){
					String hms = et.get(ref).getText().toString();
					millis = TimeUnit.SECONDS.toMillis(Long.valueOf(hms.substring(6, 8)));
					millis = millis + TimeUnit.MINUTES.toMillis(Long.valueOf(hms.substring(3, 5)));
					millis = millis + TimeUnit.HOURS.toMillis(Long.valueOf(hms.substring(0,2)));
					millis = millis + TimeUnit.DAYS.toMillis(TimeUnit.MILLISECONDS.toDays(tm.get(id)));
					tm.set(id, millis);
				}

				if(id>0){

					millis = tm.get(id) - tm.get(id-1);
					tme = String.format("%02d:%02d", 
										TimeUnit.MILLISECONDS.toMinutes(millis) - 
										TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
										TimeUnit.MILLISECONDS.toSeconds(millis) - 
										TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
					tv.get(ref).setText(tme);
				}
				if(ref>0){
					millis = tm.get(id+1) - tm.get(id);
					tme = String.format("%02d:%02d", 
										TimeUnit.MILLISECONDS.toMinutes(millis) - 
										TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
										TimeUnit.MILLISECONDS.toSeconds(millis) - 
										TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
					tv.get(ref-1).setText(tme);
				}
				break;
		}
	}
	
	@Override
	public void onBackPressed() {
		AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
		alert.setTitle("Really Exit?");
		alert.setMessage("Are you sure you want to exit?");
		alert.setNegativeButton("No", null);
		DialogInterface.OnClickListener yah = new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int w){
				MainActivity.super.onBackPressed();
			}
		};
		alert.setPositiveButton("Yes", yah).create().show();
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		tvb.get(0).setText("Save Instant State");
		save();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		
		tvb.get(0).setText("restore="+tm.toString()+"   ");
	}
	
	@Override
	public void stuff(){
		try{
			FileOutputStream afile = openFileOutput("testfile.txt", MODE_WORLD_WRITEABLE);
			OutputStreamWriter osw = new OutputStreamWriter(afile);
			osw.write("Hello,World");
			osw.close();
			
			FileInputStream bfile = openFileInput("testfile.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(bfile));
			StringBuilder total = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				total.append(line);
			}
			String[] stale = total.toString().split(",");
			line = stale[0]+"\n"+stale[1];
			Toast.makeText(getBaseContext(), line, Toast.LENGTH_LONG).show();
		}
		catch(Exception e){
			Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
		}
	}
}
