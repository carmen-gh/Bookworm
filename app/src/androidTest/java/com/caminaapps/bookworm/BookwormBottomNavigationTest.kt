package com.caminaapps.bookworm


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.caminaapps.bookworm.core.navigation.BookwormBottomNavigation
import com.caminaapps.bookworm.core.navigation.BottomNavigationScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class BookwormBottomNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: NavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            BookwormBottomNavigation(navController = navController)
        }
    }

    @Test
    fun bookwormNavHost_navigateToBookshelf_viaUI() {
        composeTestRule.onRoot().printToLog("TAG")
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")

        composeTestRule.onNodeWithTag(BottomNavigationScreen.Bookshelf.route).assertIsDisplayed()
        // add test
    }
}
