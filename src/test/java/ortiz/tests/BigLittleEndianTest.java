package ortiz.tests;

import org.junit.Assert;
import org.junit.Test;
import ortiz.utils.ByteUtils;

public class BigLittleEndianTest {

    @Test
    public void testInt2BytesBigEndian() {
        int i = 512;
        byte[] data = new byte[4];

        /*
        15	    14	    13	    12	    11	    10	    9	8	7	6	5	4	3	2	1	0
	    32768	16384	8192	4096	2048	1024	512	256	128	64	32	16	8	4	2	1
        */
        // Primeira operacao
        data[0] = (byte) (i & 0xFF);
        //1   0 |> 0   0   0   0   0   0   0   0
        //      &  1   1   1   1   1   1   1   1
        // = 0

        Assert.assertEquals(data[0], 0);

        // Segunda operacao
        //    0   0   0   0   0   0   1   0   0   0   0   0   0   0   0   0
        //>>  8
        // =  &  0   0   0   0   0   0   1   0
        // =  &  1   1   1   1   1   1   1   1
        // =  2

        data[1] = (byte) ((i >> 8) & 0xFF);

        Assert.assertEquals(data[1], 2);

        // As duas outras operacoes sao desnecessarias
        data[2] = (byte) ((i >> 16) & 0xFF);
        data[3] = (byte) ((i >> 24) & 0xFF);
    }

    @Test
    public void testBytes2IntBigEndian() {
        byte[] data = new byte[]{1, 2, 0, 0};
        int val = 0;
        val += data[3] << 24;
        val += data[2] << 16;
        val += data[1] << 8;
        val += data[0];
        Assert.assertEquals(val, 513);
    }


    @Test
    public void testInt2BytesLittleEndian() {
        int i = 512;
        byte[] data = new byte[4];

        /*
        15	    14	    13	    12	    11	    10	    9	8	7	6	5	4	3	2	1	0
	    32768	16384	8192	4096	2048	1024	512	256	128	64	32	16	8	4	2	1
        */
        // Primeira operacao
        data[3] = (byte) (i & 0xFF);
        //1   0 |> 0   0   0   0   0   0   0   0
        //      &  1   1   1   1   1   1   1   1
        // = 0

        Assert.assertEquals(0, data[3]);

        // Segunda operacao
        //    0   0   0   0   0   0   1   0   0   0   0   0   0   0   0   0
        //>>  8
        // =  &  0   0   0   0   0   0   1   0
        // =  &  1   1   1   1   1   1   1   1
        // =  2

        data[2] = (byte) ((i >> 8) & 0xFF);

        Assert.assertEquals(2, data[2]);

        // As duas outras operacoes sao desnecessarias
        data[1] = (byte) ((i >> 16) & 0xFF);
        data[0] = (byte) ((i >> 24) & 0xFF);
    }

    @Test
    public void testBytes2IntLittleEndian() {
        byte[] data = new byte[]{1, 2, 0, 0};

        int val = 0;
        val += data[0];
        val += data[1] << 8;
        val += data[2] << 16;
        val += data[3] << 24;;
        Assert.assertEquals(513, val);
    }

    @Test
    public void testByteUtils() {
        byte[] dataBigEndian = new byte[]{0, 0, 1, 1};
        int valorBigEndian = ByteUtils.toInt(dataBigEndian, true);
        Assert.assertEquals(valorBigEndian, 257);

        byte[] dataLittleEndian = new byte[]{1, 1, 0, 0};
        int valorLittleEndian = ByteUtils.toInt(dataLittleEndian, false);
        Assert.assertEquals(valorLittleEndian, 257);
    }


}
