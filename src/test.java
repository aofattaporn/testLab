import java.util.Arrays;
import java.util.Random;

public class test {
    public static void main(String[] args) {

            int[] nums = new int[5];
            int total = 100;
            Random rand = new Random();
            for (int i = 0; i < nums.length - 1; i++) {
                nums[i] = rand.nextInt(total);
                total -= nums[i];
            }
//            nums[nums.length - 1] = total;
//            Arrays.sort(nums);

        for (int i : nums){
            System.out.println(nums[i]);
        }

    }
}
