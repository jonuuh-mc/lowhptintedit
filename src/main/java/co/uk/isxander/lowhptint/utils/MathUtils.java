package co.uk.isxander.lowhptint.utils;

public final class MathUtils
{
    public static float normalize(float val, float min, float max)
    {
        return (clamp(val, min, max) - min) / (max - min);
    }

    public static float clamp(double value, double min, double max)
    {
        return (float) Math.min((Math.max(value, min)), max);
    }

    public static double ease(int type, float scale, double x)
    {
        if (type == 1)
        {
            return easeIn(scale, x);
        }
        else if (type == 2)
        {
            return easeOut(scale, x);
        }
        else if (type == 3)
        {
            return easeInOut(scale, x);
        }
        return x;
    }

    private static double easeIn(float scale, double x)
    {
        return Math.pow(x, scale);
    }

    private static double easeOut(float scale, double x)
    {
        return 1 - Math.pow(1 - x, scale);
    }

    private static double easeInOut(float scale, double x)
    {
        double firstHalf = (0.5F / Math.pow(0.5, scale)) * Math.pow(x, scale);
        double secondHalf = 1 - (Math.pow(-2 * x + 2, scale) / 2);

        return (x < 0.5) ? firstHalf : secondHalf;
    }
}
