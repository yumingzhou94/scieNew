package cransimulation;

public class Frequency {
	//��ͥ��վ����Ŀ
	private static final int BASE_STATION=4;
	public static void main(String[] args){
		
		
		//��ͥ��վ֮��ĸ��Ź�ϵ
		//�ĸ���վ��0-1-2-3-0��������ʾ����
		int[][] interferenceGraph={{0,1,0,1},
								   {1,0,1,0},
								   {0,1,0,1},
								   {1,0,1,0}};
		
		//�������ʾ���㱥�Ͷ�,��ʼΪ��
		int[] saturated=new int[BASE_STATION];
		
		//��ʾ��Ҫ����ɫ��������һ������Ժ���Ҫ��Ƶ���Ӵ�����Ŀ
		int[] colors={0,1};
		
		//��¼��ͥ��վ�Ƿ��Ѿ���ɫ
		boolean[] isColored=new boolean[BASE_STATION];
		
		//����վ0123��ɫ
		saturated[0]=colors[0];
		saturated[1]=colors[1];
		saturated[2]=colors[0];
		saturated[3]=colors[1];
		
		//ÿһ����վ���Ѿ���ɫ
		for(int i=0;i<isColored.length;++i){
			isColored[i]=true;
		}
	
		//���õ�Ƶ���Ӵ�
		int[] availablefrequency={0,1,2,3,4};
		
		//ÿ����վ����Ҫ��QOS��Ҳ��������Ҫ��Ƶ���Ӵ�,�����ʼ��
		int[] qos={2,1,3,2};
		
		//Ƶ�ʷ��������
		int[][] frequencyres={{0,1},
							  {2,3},
							  {0,1,4},
							  {2,3}};
		int[] frequencydistribute={2,2,3,2};
		
		/*�˴���ʱ������·������뷢�͹��ʣ���Ϊ��ֻ��Ҫ������Ҫ��Ƶ����Դ
		*���Կ�����ÿһ���û�����Ҫ��Ƶ����ԴΪһ������
		*���õ�����Ļ�����ʱ���ټ����Ӧ�Ƕ���hz��Ƶ�ʵȵ�
		*/
		
		//����ֱ���㣬ÿһ����ͥ��վ�û���ĿΪ1-n��ʱ���жϵĸ����Ƕ���
		//1������ÿ���û�������Ϊ1
		
		int client=4;
		double[] interruptprobability=new double[client];
		
		/***********************************�жϸ���*********************************/
		//����1-4���û����жϸ���
		for(int j=0;j<4;++j){
			int clientnumbers=j+1;
			int numbersofclientinterrupt=0;
			for(int i=0;i<BASE_STATION;++i){
				
				//ͳ��ÿһ����վ�ж��ٸ��û����ж�
				if(clientnumbers>frequencydistribute[i])
					numbersofclientinterrupt+=clientnumbers-frequencydistribute[i];
			}
			
			//�жϸ���Ϊ���жϵ��û���Ŀ�����ܵ��û���Ŀ���������������������
			interruptprobability[j]=numbersofclientinterrupt/(double)(clientnumbers*BASE_STATION);
		}
		
		//����жϵĸ���
		System.out.println("���û���Ϊ1-4ʱ���жϵĸ��ʣ�");
		for(double d:interruptprobability){
			System.out.println(d);
		}
		//������ʾ���жϵĸ��ʷֱ�Ϊ0.0 0.0 0.25 0.4375
		/************************************�жϸ���*****************************************/
		
		
		
		/**************************Ƶ��������***************************************/
		
		//�ټ���С����Ƶ��������
		double[] utility=new double[4];
		
		//�ܵ�Ƶ���Ӵ���Ŀ
		int frenumbers=0;
		for(int k=0;k<frequencydistribute.length;++k)
			frenumbers+=frequencydistribute[k];
		
		//����1-4���û���Ƶ�׸���
		for(int j=0;j<4;++j){
			int clientnumbers=j+1;
			int numbersofuse=0;
			for(int i=0;i<BASE_STATION;++i){
				
				//ͳ��ÿһ����վ�ж��ٸ��Ӵ���������
				//���С�ڣ���ôֻ���˿ͻ���Ŀ��Ƶ��
				if(clientnumbers<frequencydistribute[i])
					numbersofuse+=clientnumbers;
				//����ȫ��������
				else
					numbersofuse+=frequencydistribute[i];
			}
			
			//Ƶ��������Ϊ������Ŀ�����ܵ���Ŀ���������������������
			utility[j]=numbersofuse/(double)frenumbers;
		}
				
		//���Ƶ�׵�������
		System.out.println("���û���Ϊ1-4ʱƵ�׵������ʣ�");
		for(double u:utility){
			System.out.println(u);
		}
		
		/******************************Ƶ��������*******************************************/
		
		
		/**************************************����*****************************************/
		/*
		 * ���������������Կ��������û�����ʱ������ȫ���Ļ�վ��Ƶ�������ʵͣ�Ҳ����˵��
		 * �������Ĵ�
		 * 
		 * ���û���Ŀ��ʱ��Ƶ�������ʽϸߣ�����ʱ�û����жϵĸ��ʴ�
		 * 
		 * ����ʵ������滹ֻ��һ�����װ汾������Ҫ���Ĺ�����һ�¼��㣺
		 * 1������Ƶ�׷���Ĺ���
		 *
		 * 2����ʵ�����ݹ�ģ����
		 * 
		 * 3�����ŵ������Ⱦ���ʵ������Ĳ�������
		 * 
		 * 4�����Ľ����㷨���룬�������Ƿ����㷨�����룬����Ͼ������ݸĽ��㷨
		 * 
		 * 5���������ڸĽ��㷨���жϸ�����Ƶ���������Լ���վ�ȵĿ����ر����,����һ�����������ȵĽ�ʡ���
		 * 
		 */
		/***************************************����****************************************/
	}
}
