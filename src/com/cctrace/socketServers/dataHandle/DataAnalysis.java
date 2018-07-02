package com.cctrace.socketServers.dataHandle;

import java.util.HashMap;
import java.util.Map;

import com.cctrace.entity.Alert;
import com.cctrace.entity.Ccdata;
import com.cctrace.entity.Ccdata1;
import com.cctrace.entity.CommandStore;
import com.cctrace.entity.Container;
import com.cctrace.entity.LwAlarm;
import com.cctrace.entity.OurCcdata1;
import com.cctrace.service.DaoService;
import com.cctrace.socketServers.util.DealDateData;
import com.github.pagehelper.util.StringUtil;

//@Controller
public class DataAnalysis {

	// @Resource(name = "daoService")

	public DaoService daoService;

	public DataAnalysis(DaoService daoService) {
		this.daoService = daoService;
	}

	private ModeDate modeDate = new ModeDate();

	public String dataProcess(String data, String sevNum) {

		try {
			Container container = daoService.selectContainerByDeviceId(sevNum);
			if (container == null) {
				return null;
			}

			Long currentLongTime = System.currentTimeMillis();
			DealDateData dealDateData = new DealDateData();
			String alertStrFormatNowDate = dealDateData
					.getStringDate(currentLongTime);

			Map<String, String> mapJ = null;
			// data="93 C8 11 29 F2 03 59 03 52 03 02 03 96 90 9A";
			// data="93 C8 10 02 00 93";
			Integer dataL = data.length();
			Ccdata1 cc = daoService.selectLastCcdataByContainerId1(container
					.getContainerId());
			Ccdata1 cc1 = cc;
			if (cc == null) {
				return null;
			}
			if (data.equals("93 C8 10 02 00 93")) {
				cc1.setRefSwiState("off");
				daoService.updateCcdataById1(cc1);
				return null;
			} else {
				cc.setRefSwiState("on");
				daoService.updateCcdataById1(cc1);
			}
			try {
				Map<String,Object> mapCS=new HashMap<String,Object>();
				Long csTime=System.currentTimeMillis()-190000;
				mapCS.put("value1", cc1.getContainerId());
				mapCS.put("value2", csTime);
				CommandStore cs=daoService.selectCommandStoreByMap(mapCS);
				if(cs != null){
					cs.setStatus("Y");
					daoService.updateCommandStoreById(cs);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			String brand = data.split("\\s+")[0];// 冷机型号
			String pid = data.split("\\s+")[1];// PID值
			String a = data.split("\\s+")[2];// a的值，a必须有，判断后面b预警值信息
			String at = "00000000"
					+ Integer.toBinaryString((Integer.parseInt(a, 16)));
			String a8 = at.substring(at.length() - 8, at.length());
			String a41 = a8.substring(a8.length() - 4, a8.length());
			String a87 = a8.substring(0, 2);

			String dtem = null; // 回风温度
			String etem = null; // 出风温度
			String ftem = null; // 设定温度
			String gtem = null; // 蒸发线圈温度
			Integer alarm = null;// 预警值信息

			if (a41.equals("0000")) {

				String c = data.split("\\s+")[3];// c值必须有，来判断解析温度，如回风、出风温度
				String ct1 = "00000000"
						+ Integer.toBinaryString(Integer.parseInt(c, 16));
				String ct = ct1.substring(ct1.length() - 8, ct1.length());
				String c8 = ct.substring(0, 1);
				String c7 = ct.substring(1, 2);
				String c6 = ct.substring(2, 3);
				String c5 = ct.substring(3, 4);
				String c2 = ct.substring(6, 7);
				if (c8.equals("0")) {

					if (c7.equals("0")) {

						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[4];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[4];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[5];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[6];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[4];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[5];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[6];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[6];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[7];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = 0;// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
									gtem0 = gtem0 - 65536;
								} else {
									gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								}
								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[8];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}
						}

					} else {
						String e1 = data.split("\\s+")[4];// 出风温度
						String e11 = e1.substring(0, 1);
						String e2 = data.split("\\s+")[5];// 出风温度
						String e = e1 + e2;// 由于用8位，将d1和d2合并在一起转换成十进制
						double etem0 = Integer.parseInt(e, 16);// 解析完数据是华氏度
						if (e11.equals("F")) {
							etem0 = Integer.parseInt(e, 16);// 解析完数据是华氏度
							etem0 = etem0 - 65536;
						}

						String etem1 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[0];
						String etem2 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[1];
						etem = etem1 + "." + etem2.substring(0, 1);// 出风温度
						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[6];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[6];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[7];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[8];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[6];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[7];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[8];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[8];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[9];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[10];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}
						}
					}

				} else {
					String d1 = data.split("\\s+")[4];// 回风温度
					String d11 = d1.substring(0, 1);
					String d2 = data.split("\\s+")[5];// 回风温度
					String d = d1 + d2;// 由于用8位，将d1和d2合并在一起转换成十进制
					double dtem0 = Integer.parseInt(d, 16);// 解析完数据是华氏度
					if (d11.equals("F")) {
						dtem0 = Integer.parseInt(d, 16);
						dtem0 = dtem0 - 65536;
					}

					String dtem1 = ("" + (dtem0 / 10 - 32) / 1.8).split("\\.")[0];
					String dtem2 = ("" + (dtem0 / 10 - 32) / 1.8).split("\\.")[1];
					dtem = dtem1 + "." + dtem2.substring(0, 1);// 回风温度
					if (c7.equals("0")) {

						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[6];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[6];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[7];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[8];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[6];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[7];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[8];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[8];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[9];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[10];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}
						}

					} else {
						String e1 = data.split("\\s+")[6];// 出风温度
						String e11 = e1.substring(0, 1);
						String e2 = data.split("\\s+")[7];// 出风温度
						String e = e1 + e2;// 由于用8位，将d1和d2合并在一起转换成十进制
						double etem0 = Integer.parseInt(e, 16);// 解析完数据是华氏度
						if (e11.equals("F")) {
							etem0 = Integer.parseInt(e, 16);
							etem0 = etem0 - 65536;
						}

						String etem1 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[0];
						String etem2 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[1];
						etem = etem1 + "." + etem2.substring(0, 1);// 出风温度
						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[8];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[8];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[9];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[10];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[8];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[9];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[10];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[10];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[11];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[12];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}
							}
						}
					}
				}

			} else {
				String b = data.split("\\s+")[3];// 来判断预警值，根据b值对应预警信息
				String bt = "00000000"
						+ Integer.toBinaryString(Integer.parseInt(b, 16));
				int bs = Integer.parseInt(b, 16);
				String b8 = bt.substring(bt.length() - 8, bt.length());
				String b87 = b8.substring(0, 2);
				int ww = 0;
				if (b87.equals("00")) {
					ww = 0;
				} else if (b87.equals("01")) {
					ww = 1;
				} else if (b87.equals("10")) {
					ww = 2;
				} else if (b87.equals("11")) {
					ww = 3;
				}
				alarm = bs + 256 * ww;// 预警值，根据alarm来判断什么报警信息
				String c = data.split("\\s+")[4];// c值必须有，来判断解析温度，如回风、出风温度
				String ct1 = "00000000"
						+ Integer.toBinaryString(Integer.parseInt(c, 16));
				String ct = ct1.substring(ct1.length() - 8, ct1.length());
				String c8 = ct.substring(0, 1);
				String c7 = ct.substring(1, 2);
				String c6 = ct.substring(2, 3);
				String c5 = ct.substring(3, 4);
				String c2 = ct.substring(6, 7);
				if (c8.equals("0")) {

					if (c7.equals("0")) {

						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[5];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[5];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[6];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[7];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[5];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[6];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[7];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[7];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[8];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[9];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}
						}

					} else {
						String e1 = data.split("\\s+")[5];// 出风温度
						String e11 = e1.substring(0, 1);
						String e2 = data.split("\\s+")[6];// 出风温度
						String e = e1 + e2;// 由于用8位，将d1和d2合并在一起转换成十进制
						double etem0 = Integer.parseInt(e, 16);// 解析完数据是华氏度
						if (e11.equals("F")) {
							etem0 = Integer.parseInt(e, 16);
							etem0 = etem0 - 65536;
						}

						String etem1 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[0];
						String etem2 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[1];
						etem = etem1 + "." + etem2.substring(0, 1);// 出风温度
						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[7];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[7];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[8];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[9];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[7];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[8];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[9];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[9];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[10];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[11];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}
						}
					}

				} else {
					String d1 = data.split("\\s+")[5];// 回风温度
					String d11 = d1.substring(0, 1);
					String d2 = data.split("\\s+")[6];// 回风温度
					String d = d1 + d2;// 由于用8位，将d1和d2合并在一起转换成十进制
					double dtem0 = Integer.parseInt(d, 16);// 解析完数据是华氏度
					if (d11.equals("F")) {
						dtem0 = Integer.parseInt(d, 16);
						dtem0 = dtem0 - 65536;
					}

					String dtem1 = ("" + (dtem0 / 10 - 32) / 1.8).split("\\.")[0];
					String dtem2 = ("" + (dtem0 / 10 - 32) / 1.8).split("\\.")[1];
					dtem = dtem1 + "." + dtem2.substring(0, 1);// 回风温度
					if (c7.equals("0")) {

						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[7];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[7];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[8];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[9];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[7];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[8];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[9];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[9];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[10];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[11];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}
						}

					} else {
						String e1 = data.split("\\s+")[7];// 出风温度
						String e11 = e1.substring(0, 1);
						String e2 = data.split("\\s+")[8];// 出风温度
						String e = e1 + e2;// 由于用8位，将d1和d2合并在一起转换成十进制
						double etem0 = Integer.parseInt(e, 16);// 解析完数据是华氏度
						if (e11.equals("F")) {
							etem0 = Integer.parseInt(e, 16);
							etem0 = etem0 - 65536;
						}

						String etem1 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[0];
						String etem2 = ("" + (etem0 / 10 - 32) / 1.8).split("\\.")[1];
						etem = etem1 + "." + etem2.substring(0, 1);// 出风温度
						if (c6.equals("0")) {

							if (c5.equals("0")) {

								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[9];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[9];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[10];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[11];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							}

						} else {
							String f1 = data.split("\\s+")[9];// 设定温度
							String f11 = f1.substring(0, 1);
							String f2 = data.split("\\s+")[10];// 设定温度
							String f = f1 + f2;// 由于用8位，将d1和d2合并在一起转换成十进制
							double ftem0 = Integer.parseInt(f, 16);// 解析完数据是华氏度
							if (f11.equals("F")) {
								ftem0 = Integer.parseInt(f, 16);
								ftem0 = ftem0 - 65536;
							}

							String ftem1 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[0];
							String ftem2 = ("" + (ftem0 / 10 - 32) / 1.8)
									.split("\\.")[1];
							ftem = ftem1 + "." + ftem2.substring(0, 1);// 设定温度
							if (c5.equals("0")) {
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[11];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}

							} else {
								String g1 = data.split("\\s+")[11];// 蒸发线圈温度
								String g11 = g1.substring(0, 1);
								String g2 = data.split("\\s+")[12];// 蒸发线圈温度
								String g = g1 + g2;// 由于用8位，将d1和d2合并在一起转换成十进制
								double gtem0 = Integer.parseInt(g, 16);// 解析完数据是华氏度
								if (g11.equals("F")) {
									gtem0 = Integer.parseInt(g, 16);
									gtem0 = gtem0 - 65536;
								}

								String gtem1 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[0];
								String gtem2 = ("" + (gtem0 / 10 - 32) / 1.8)
										.split("\\.")[1];
								gtem = gtem1 + "." + gtem2.substring(0, 1);// 蒸发线圈温度
								if (c2.equals("0")) {

								} else {
									String j = data.split("\\s+")[13];
									Map<String, String> putJ = new HashMap<String, String>();
									putJ.put("c2", c2);
									putJ.put("j", j);
									mapJ = modeDate.modeDateHandle(putJ);
								}
							}
						}
					}
				}
			}

			Ccdata cd = new Ccdata();
			cd.setContainerId(container.getContainerId());
			cd.setDeviceId(sevNum);
			cd.setNowTime(alertStrFormatNowDate);
			cd.setNowLongTime(currentLongTime);
			cc.setNowTime(alertStrFormatNowDate);
			cc.setNowLongTime(currentLongTime);
			String containerId = container.getContainerId();
			OurCcdata1 ourCcdata = daoService.selectOurCcdataByDeviceId1(sevNum);
			Double lat = null;
			Double lon = null;
			Integer companyId = 1;
			if (container != null) {
				companyId = container.getCompanyId();
			}
			if (ourCcdata != null) {
				lat = ourCcdata.getLat();
				lon = ourCcdata.getLon();
				cd.setLat(lat);
				cd.setLon(lon);
				cd.setBackDoorState(ourCcdata.getBackDoorState());
				cd.setGpsPower(ourCcdata.getGpsPower());
				cd.setOilLevel(ourCcdata.getOilLevel());
				cd.setOilTemp(ourCcdata.getOilLevel());
				cd.setTankHum(ourCcdata.getBoxHum());
				cd.setTailBoxTemp(ourCcdata.getTailBoxTemp());
				cc.setLat(lat);
				cc.setLon(lon);
				cc.setBackDoorState(ourCcdata.getBackDoorState());
				cc.setGpsPower(ourCcdata.getGpsPower());
				cc.setOilLevel(ourCcdata.getOilLevel());
				cc.setOilTemp(ourCcdata.getOilLevel());
				cc.setTankHum(ourCcdata.getBoxHum());
				cc.setTailBoxTemp(ourCcdata.getTailBoxTemp());

			}
			cd.setCompanyId(companyId);
			if (StringUtil.isNotEmpty(dtem) && container != null) {
				Double tem = Double.parseDouble(dtem);
				if(tem>50 || tem<-50){
					tem=cc.getBackWindTemp();
				}
				cd.setBackWindTemp(tem);
				cc.setBackWindTemp(tem);
				Double minBackWindTemp = container.getMinBackWindTemp();
				Double maxBackWindTemp = container.getMaxBackWindTemp();
				Alert alert = new Alert();
				alert.setAlertLongTime(currentLongTime);
				alert.setAlertTime(alertStrFormatNowDate);
				alert.setReaded("no");
				alert.setLat(lat);
				alert.setLon(lon);
				alert.setContainerId(containerId);
				alert.setAlertType("回风温度预警");
				alert.setCompanyId(cc.getCompanyId());
				alert.setGpsPosition(ourCcdata.getGpsPosition());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("containerId", container.getContainerId());
				map.put("startTime", System.currentTimeMillis()
						- (24 * 60 * 60 * 1000));
				map.put("endTime", System.currentTimeMillis());

				if (minBackWindTemp == null || maxBackWindTemp == null) {

				} else if (tem <= minBackWindTemp && minBackWindTemp != null) {
					map.put("alarm_num", 10001);
					if (daoService.selectAlertOnByContainerId(map) == null) {
						alert.setAlarm_num(10001);
						alert.setAlertContent("实际温度小于设定回风温度！");
						alert.setCompanyId(container.getCompanyId());
//						daoService.insertAlert(alert);
					}
				} else if (tem >= maxBackWindTemp && maxBackWindTemp != null) {
					map.put("alarm_num", 10001);
					if (daoService.selectAlertOnByContainerId(map) == null) {
						alert.setAlarm_num(10002);
						alert.setAlertContent("实际温度大于设定回风温度！");
						alert.setCompanyId(container.getCompanyId());
//						daoService.insertAlert(alert);
					}
				}

			}
			if (StringUtil.isNotEmpty(etem) && container != null) {
				Double tem = Double.parseDouble(etem);
				if(tem>50 || tem<-50){
					tem=cc.getChuWindTemp();
				}
				cd.setChuWindTemp(tem);
				cc.setChuWindTemp(tem);
				Double minChuWindTemp = container.getMinChuWindTemp();
				Double maxChuWindTemp = container.getMaxChuWindTemp();
				Alert alert = new Alert();
				alert.setAlertLongTime(currentLongTime);
				alert.setAlertTime(alertStrFormatNowDate);
				alert.setReaded("no");
				alert.setLat(lat);
				alert.setLon(lon);
				alert.setContainerId(containerId);
				alert.setAlertType("出风温度预警");
				alert.setCompanyId(cc.getCompanyId());
				alert.setGpsPosition(ourCcdata.getGpsPosition());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("containerId", container.getContainerId());
				map.put("startTime", System.currentTimeMillis()
						- (24 * 60 * 60 * 1000));
				map.put("endTime", System.currentTimeMillis());
				if (minChuWindTemp == null || maxChuWindTemp == null) {

				} else if (tem <= minChuWindTemp && minChuWindTemp != null) {
					map.put("alarm_num", 10011);
					if (daoService.selectAlertOnByContainerId(map) == null) {
						alert.setAlarm_num(10011);
						alert.setAlertContent("实际温度小于设定出风温度！");
						alert.setCompanyId(container.getCompanyId());
//						daoService.insertAlert(alert);
					}
				} else if (tem >= maxChuWindTemp && maxChuWindTemp != null) {
					map.put("alarm_num", 10012);
					if (daoService.selectAlertOnByContainerId(map) == null) {
						alert.setAlarm_num(10012);
						alert.setAlertContent("实际温度大于设定出风温度！");
						alert.setCompanyId(container.getCompanyId());
//						daoService.insertAlert(alert);
					}
				}
			}
			if (StringUtil.isNotEmpty(ftem) && container != null) {
				Double tem = Double.parseDouble(ftem);
				if(tem>50 || tem<-50){
					tem=cc.getTempSet();
				}
				cd.setTempSet(tem);
				cc.setTempSet(tem);
				/*
				 * Double setTemp=container.getSetTemp(); Alert alert=new Alert();
				 * alert.setAlertLongTime(currentLongTime);
				 * alert.setAlertTime(alertStrFormatNowDate); alert.setReaded("no");
				 * alert.setLat(lat); alert.setLon(lon);
				 * alert.setContainerId(containerId); alert.setAlertType("设定温度预警");
				 * if(setTemp==null){
				 * 
				 * } else if(tem <=setTemp && setTemp!=null){
				 * alert.setAlertContent("实际温度小于设定温度!");
				 * daoService.insertAlert(alert); }else if(tem>=setTemp &&
				 * setTemp!=null){ alert.setAlertContent("实际温度大于设定温度!");
				 * daoService.insertAlert(alert); }
				 */

			}
			if (StringUtil.isNotEmpty(gtem) && container != null) {
				Double tem = Double.parseDouble(gtem);
				cd.setCoilTemp(tem);
				cc.setCoilTemp(tem);
				/*
				 * Alert alert=new Alert(); alert.setAlertLongTime(currentLongTime);
				 * alert.setAlertTime(alertStrFormatNowDate); alert.setReaded("no");
				 * alert.setLat(lat); alert.setLon(lon);
				 * alert.setContainerId(containerId); Double
				 * minCoilTemp=container.getMinCoilTemp(); Double
				 * maxCoilTemp=container.getMaxCoilTemp();
				 * alert.setAlertType("蒸发线圈温度预警"); if(minCoilTemp==null ||
				 * maxCoilTemp==null){
				 * 
				 * } else if(tem <=minCoilTemp && minCoilTemp!=null ){
				 * alert.setAlertContent("实际温度小于蒸发线圈设定温度!");
				 * daoService.insertAlert(alert); } else if(tem>=maxCoilTemp &&
				 * maxCoilTemp!=null){ alert.setAlertContent("实际温度大于蒸发线圈设定温度!");
				 * daoService.insertAlert(alert); }
				 */

			}
			String j51 = null;
			if (mapJ != null && container != null) {
				String refRunMode = mapJ.get("j5");
				cd.setRefRunMode(refRunMode);
				cd.setOperationMode(mapJ.get("j4"));
				cc.setRefRunMode(refRunMode);
				cc.setOperationMode(mapJ.get("j4"));
				String backDoorState = mapJ.get("j3");
				cd.setBackDoorState(backDoorState);
				cd.setPowerType(mapJ.get("j2"));
				cd.setWorkMode(mapJ.get("workMode"));
				cc.setBackDoorState(backDoorState);
				cc.setPowerType(mapJ.get("j2"));
				cc.setWorkMode("无状态");
				cc.setWorkMode(mapJ.get("workMode"));
				cc.setRefSwiState("on");
				j51 = mapJ.get("j51");
			}

			if (alarm != null) {
				LwAlarm lwAlarm = daoService.selectLwAlarmByAlarm_num(alarm);
				if (lwAlarm != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("containerId", container.getContainerId());
					map.put("startTime", System.currentTimeMillis()
							- (24 * 60 * 60 * 1000));
					map.put("endTime", System.currentTimeMillis());
					map.put("alarm_num", alarm);
					Alert alt = daoService.selectAlertOnByContainerId(map);
					if (alt == null) {
						Alert alert = new Alert();
						alert.setAlertContent(lwAlarm.getChi_con());
						alert.setContainerId(containerId);
						alert.setAlertLongTime(currentLongTime);
						alert.setAlertTime(alertStrFormatNowDate);
						alert.setLat(lat);
						alert.setLon(lon);
						alert.setReaded("no");
						alert.setAlertType("TKalarm");
						alert.setAlarm_num(alarm);
						alert.setCompanyId(cc.getCompanyId());
						alert.setGpsPosition(ourCcdata.getGpsPosition());
						alert.setCompanyId(container.getCompanyId());
						daoService.insertAlert(alert);
					} else {
						alt.setUpdateLongTime(System.currentTimeMillis());
						daoService.updateAlertTimeById(alt);
					}
				}
			}

			/*
			if (a41.equals("1111")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("containerId", container.getContainerId());
				map.put("startTime", System.currentTimeMillis()
						- (24 * 60 * 60 * 1000));
				map.put("endTime", System.currentTimeMillis());
				map.put("alarm_num", 10000);
				Alert alt = daoService.selectAlertOnByContainerId(map);
				if (alt == null) {
					Alert alert = new Alert();
					alert.setAlertContent("冷机关机或严重性系统故障！");
					alert.setContainerId(containerId);
					alert.setAlertLongTime(currentLongTime);
					alert.setAlertTime(alertStrFormatNowDate);
					alert.setLat(lat);
					alert.setLon(lon);
					alert.setReaded("no");
					alert.setAlertType("TKalarm");
					alert.setAlarm_num(10000);
					alert.setGpsPosition(ourCcdata.getGpsPosition());
					alert.setCompanyId(cc.getCompanyId());
					alert.setCompanyId(container.getCompanyId());
					daoService.insertAlert(alert);
				} else {
					alt.setUpdateLongTime(System.currentTimeMillis());
					daoService.updateAlertTimeById(alt);
				}
			}
			*/
			
			

			daoService.insertCcdata(cd);
			daoService.updateCcdataById1(cc);
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("冷王数据解析异常！");
		}
		return null;
	}
		
}
