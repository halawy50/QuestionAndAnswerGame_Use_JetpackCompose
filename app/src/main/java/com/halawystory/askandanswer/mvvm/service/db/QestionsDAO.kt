package com.halawystory.askandanswer.mvvm.service.db

import androidx.room.*
import com.halawystory.askandanswer.mvvm.model.Difficulty
import com.halawystory.askandanswer.mvvm.model.LevelsOpenEntityDataBase
import com.halawystory.askandanswer.mvvm.model.SolveQuestionsEntityDataBase
import com.halawystory.askandanswer.mvvm.model.StateLevel

/**
 * واجهة الوصول إلى قاعدة البيانات للأسئلة والمستويات
 */
@Dao
interface QuestionsDao {


    @Query("SELECT * FROM LevelsOpenEntityDataBase ORDER BY Level DESC LIMIT 1")
    fun getMyLevel(): LevelsOpenEntityDataBase


    /**
     * استرجاع جميع المستويات المفتوحة
     */
    @Query("SELECT * FROM LevelsOpenEntityDataBase")
    suspend fun retriveAllMyLevels(): List<LevelsOpenEntityDataBase>

    /**
     * زيادة عدد الأسئلة المحلولة لمستوى محدد
     */
    @Query("UPDATE LevelsOpenEntityDataBase SET solveQuestions = solveQuestions + 1 WHERE difficult = :difficulty")
    fun incrementSolveQuestions(difficulty: Difficulty): Int

    /**
     * إضافة مستوى جديد
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOpenLevel(levelsOpenEntityDataBase: LevelsOpenEntityDataBase): Long

    /**
     * جلب مستوى واحد حسب مستوى الصعوبة
     */
    @Query("SELECT * FROM LevelsOpenEntityDataBase WHERE difficult = :difficulty LIMIT 1")
    fun getSingleLevel(difficulty: Difficulty): LevelsOpenEntityDataBase

    /**
     * إضافة سؤال محلول
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSolveQuestion(solveQuestion: SolveQuestionsEntityDataBase): Long

    /**
     * جلب جميع الأسئلة المحلولة لمستوى محدد
     */
    @Query("SELECT * FROM SolveQuestionsEntityDataBase WHERE difficulty = :difficulty")
    fun getAllMySolveQuestion(difficulty: Difficulty): List<SolveQuestionsEntityDataBase>

    @Transaction
    fun clearQuestionsAndResetSolveCount(difficulty: Difficulty): Boolean {
        val deletedRows = deleteAllQuestionByDifficulty(difficulty)
        val updatedRows = resetSolveQuestionsByDifficulty(difficulty)

        return deletedRows > 0 || updatedRows > 0  // ✅ إرجاع true إذا تم تنفيذ أي تعديل
    }

    @Query("DELETE FROM SolveQuestionsEntityDataBase WHERE difficulty = :difficulty")
    fun deleteAllQuestionByDifficulty(difficulty: Difficulty): Int

    @Query("UPDATE LevelsOpenEntityDataBase SET solveQuestions = 0 WHERE difficult = :difficulty")
    fun resetSolveQuestionsByDifficulty(difficulty: Difficulty): Int


    @Query("UPDATE LevelsOpenEntityDataBase SET state = :stateLevel WHERE difficult = :difficulty")
    fun updateLevel(stateLevel: StateLevel, difficulty: Difficulty):Int

}