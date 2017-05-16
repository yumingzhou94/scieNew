package cransimulation;

public class Frequency {
	//家庭基站的数目
	private static final int BASE_STATION=4;
	public static void main(String[] args){
		
		
		//家庭基站之间的干扰关系
		//四个基站，0-1-2-3-0，相连表示干扰
		int[][] interferenceGraph={{0,1,0,1},
								   {1,0,1,0},
								   {0,1,0,1},
								   {1,0,1,0}};
		
		//用数组表示顶点饱和度,初始为零
		int[] saturated=new int[BASE_STATION];
		
		//表示需要的颜色数，具体一点就是以后需要的频率子带的数目
		int[] colors={0,1};
		
		//记录家庭基站是否已经着色
		boolean[] isColored=new boolean[BASE_STATION];
		
		//给基站0123着色
		saturated[0]=colors[0];
		saturated[1]=colors[1];
		saturated[2]=colors[0];
		saturated[3]=colors[1];
		
		//每一个基站都已经着色
		for(int i=0;i<isColored.length;++i){
			isColored[i]=true;
		}
	
		//可用的频率子带
		int[] availablefrequency={0,1,2,3,4};
		
		//每个基站所需要的QOS，也就是所需要的频率子带,随机初始化
		int[] qos={2,1,3,2};
		
		//频率分配分配结果
		int[][] frequencyres={{0,1},
							  {2,3},
							  {0,1,4},
							  {2,3}};
		int[] frequencydistribute={2,2,3,2};
		
		/*此处暂时不考虑路径损耗与发送功率，因为我只需要计算需要的频谱资源
		*所以可以令每一个用户所需要的频率资源为一个整数
		*当用到具体的环境中时，再计算对应是多少hz的频率等等
		*/
		
		//下面分别计算，每一个家庭基站用户数目为1-n的时候，中断的概率是多少
		//1，假设每个用户的需求为1
		
		int client=4;
		double[] interruptprobability=new double[client];
		
		/***********************************中断概率*********************************/
		//计算1-4个用户的中断概率
		for(int j=0;j<4;++j){
			int clientnumbers=j+1;
			int numbersofclientinterrupt=0;
			for(int i=0;i<BASE_STATION;++i){
				
				//统计每一个基站有多少个用户被中断
				if(clientnumbers>frequencydistribute[i])
					numbersofclientinterrupt+=clientnumbers-frequencydistribute[i];
			}
			
			//中断概率为被中断的用户数目除以总的用户数目，并将结果保存在数组中
			interruptprobability[j]=numbersofclientinterrupt/(double)(clientnumbers*BASE_STATION);
		}
		
		//输出中断的概率
		System.out.println("在用户数为1-4时被中断的概率：");
		for(double d:interruptprobability){
			System.out.println(d);
		}
		//如结果所示，中断的概率分别为0.0 0.0 0.25 0.4375
		/************************************中断概率*****************************************/
		
		
		
		/**************************频谱利用率***************************************/
		
		//再计算小区的频谱利用率
		double[] utility=new double[4];
		
		//总的频率子带数目
		int frenumbers=0;
		for(int k=0;k<frequencydistribute.length;++k)
			frenumbers+=frequencydistribute[k];
		
		//计算1-4个用户的频谱概率
		for(int j=0;j<4;++j){
			int clientnumbers=j+1;
			int numbersofuse=0;
			for(int i=0;i<BASE_STATION;++i){
				
				//统计每一个基站有多少个子带被利用了
				//如果小于，那么只用了客户数目的频带
				if(clientnumbers<frequencydistribute[i])
					numbersofuse+=clientnumbers;
				//否则，全部利用了
				else
					numbersofuse+=frequencydistribute[i];
			}
			
			//频谱利用率为利用数目除以总的数目，并将结果保存在数组中
			utility[j]=numbersofuse/(double)frenumbers;
		}
				
		//输出频谱的利用率
		System.out.println("在用户数为1-4时频谱的利用率：");
		for(double u:utility){
			System.out.println(u);
		}
		
		/******************************频谱利用率*******************************************/
		
		
		/**************************************分析*****************************************/
		/*
		 * 由上述仿真结果可以看出，当用户数少时，开启全部的基站，频谱利用率低，也就是说明
		 * 能量消耗大
		 * 
		 * 当用户数目多时，频谱利用率较高，但此时用户被中断的概率大
		 * 
		 * 但其实这个仿真还只是一个简易版本，后续要做的工作有一下几点：
		 * 1、完善频谱分配的过程
		 *
		 * 2、将实验数据规模扩大
		 * 
		 * 3、将信道特征等具有实际意义的参数引入
		 * 
		 * 4、将改进的算法引入，具体点就是分类算法的引入，并结合具体数据改进算法
		 * 
		 * 5、分析基于改进算法的中断概率与频谱利用率以及基站等的开启关闭情况,并进一步分析能量等的节省情况
		 * 
		 */
		/***************************************分析****************************************/
	}
}
