# First, we observe that for any given sequence that is in descending order, no next larger permutation is possible. For example, no next permutation is possible for the following array:
# [9, 5, 4, 3, 1]
# We need to find the first pair of two successive numbers a[i]a[i] and a[i-1]a[i−1], from the right, which satisfy a[i] > a[i-1]. Now, no rearrangements to the right of a[i-1] can create a larger permutation since that subarray consists of numbers in descending order. Thus, we need to rearrange the numbers to the right of a[i-1] including itself.
# Now, what kind of rearrangement will produce the next larger number? We want to create the permutation just larger than the current one. Therefore, we need to replace the number a[i-1]with the number which is just larger than itself among the numbers lying to its right section, say a[j].
# We swap the numbers a[i-1]] and a[j]. We now have the correct number at index i-1. But still the current permutation isn't the permutation that we are looking for. We need the smallest permutation that can be formed by using the numbers only to the right of a[i-1]. Therefore, we need to place those numbers in ascending order to get their smallest permutation.
# But, recall that while scanning the numbers from the right, we simply kept decrementing the index until we found the pair a[i] and a[i-1] where, a[i] > a[i-1]. Thus, all numbers to the right of a[i-1] were already sorted in descending order. Furthermore, swapping a[i-1] and a[j] didn't change that order. Therefore, we simply need to reverse the numbers following a[i-1]a[i−1] to get the next smallest lexicographic permutation.

class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        if len(nums) == 0: return nums
        if len(nums) == 1: return nums
        # step1 : find the first decreasing element from right end
        for i in reversed(range(len(nums))):
            if i != 0 and nums[i] > nums[i-1]:
                # nums[i-1] is the first decreasing element from right end
                fd, idx_fd = nums[i-1], i-1
                break
            elif i == 0:
                nums.sort()
                return nums
        # step2 : find first ele that just larger than fd starting from a[i]
        for i in reversed(range(idx_fd+1, len(nums))):
            if nums[i] > fd:
                # nums[i] is the first ele that just larger than fd
                # step3: swap jl and fd
                nums[idx_fd], nums[i] = nums[i], nums[idx_fd]
                break 
        # step4: reverse elements after idx_fd to ascending order
        nums[idx_fd+1:] = nums[:idx_fd:-1]
        return nums
