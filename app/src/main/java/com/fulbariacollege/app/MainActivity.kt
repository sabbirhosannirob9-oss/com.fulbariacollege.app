
package com.fulbariacollege.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF0A1655)
private val Gold = Color(0xFFD4AF37)
private val Page = Color(0xFFF4F6FB)
private val Muted = Color(0xFF69738B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(10, 22, 85)
        window.navigationBarColor = android.graphics.Color.WHITE

        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    primary = Navy,
                    secondary = Gold,
                    background = Page
                )
            ) {
                CollegeApp()
            }
        }
    }
}

private data class Tab(
    val title: String,
    val icon: ImageVector
)

private val tabs = listOf(
    Tab("হোম", Icons.Filled.Home),
    Tab("নোটিশ", Icons.Filled.Notifications),
    Tab("একাডেমিক", Icons.Filled.School),
    Tab("রুটিন", Icons.Filled.CalendarMonth),
    Tab("আরও", Icons.Filled.MoreHoriz)
)

@Composable
private fun CollegeApp() {
    var selected by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Page,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 6.dp
            ) {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = { selected = index },
                        icon = {
                            Icon(
                                tab.icon,
                                contentDescription = tab.title
                            )
                        },
                        label = {
                            Text(tab.title, fontSize = 10.sp)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Navy,
                            selectedTextColor = Navy,
                            indicatorColor = Color(0xFFE8ECFA),
                            unselectedIconColor = Muted,
                            unselectedTextColor = Muted
                        )
                    )
                }
            }
        }
    ) { padding ->
        AnimatedContent(
            targetState = selected,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "tab_transition",
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) { page ->
            when (page) {
                0 -> HomeScreen { selected = it }
                1 -> ContentScreen(
                    "সাম্প্রতিক নোটিশ",
                    "কলেজের ঘোষণা ও বিজ্ঞপ্তি",
                    listOf(
                        "নোটিশ প্রকাশিত হলে এখানে দেখানো হবে",
                        "পরীক্ষা সংক্রান্ত বিজ্ঞপ্তি",
                        "ভর্তি সংক্রান্ত ঘোষণা"
                    ),
                    Icons.Filled.Campaign
                )
                2 -> ContentScreen(
                    "একাডেমিক",
                    "শিক্ষার্থী ও অভিভাবকদের তথ্য",
                    listOf(
                        "বিভাগ ও বিষয়সমূহ",
                        "পরীক্ষার ফলাফল",
                        "ভর্তি সংক্রান্ত তথ্য",
                        "শিক্ষক ও কর্মচারী"
                    ),
                    Icons.Filled.School
                )
                3 -> ContentScreen(
                    "ক্লাস রুটিন",
                    "ক্লাস ও পরীক্ষার সময়সূচি",
                    listOf(
                        "প্রথম বর্ষের রুটিন",
                        "দ্বিতীয় বর্ষের রুটিন",
                        "তৃতীয় ও চতুর্থ বর্ষের রুটিন",
                        "পরীক্ষার সময়সূচি"
                    ),
                    Icons.Filled.CalendarMonth
                )
                else -> ContentScreen(
                    "আরও সেবা",
                    "কলেজের অন্যান্য বিভাগ",
                    listOf(
                        "ফটো গ্যালারি",
                        "রক্তদাতা তথ্য",
                        "যোগাযোগ",
                        "BNCC ও স্কাউট"
                    ),
                    Icons.Filled.GridView
                )
            }
        }
    }
}

@Composable
private fun HomeScreen(onNavigate: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF17276F), Navy)
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.AccountBalance,
                        contentDescription = null,
                        tint = Gold,
                        modifier = Modifier.size(34.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            "FULBARIYA COLLEGE",
                            color = Gold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp,
                            fontSize = 12.sp
                        )
                        Text(
                            "MYMENSINGH, BANGLADESH",
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }

                Spacer(Modifier.height(26.dp))

                Text(
                    "ফুলবাড়ীয়া কলেজ",
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "শিক্ষা, জ্ঞান ও সম্ভাবনার পথে",
                    color = Color(0xFFE1E7FA),
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(18.dp))

                Text(
                    "প্রতিষ্ঠিত: ১৫ আগস্ট ১৯৭২  •  EIIN: 111516",
                    color = Color.White,
                    fontSize = 11.sp
                )
            }
        }

        item {
            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(
                    "আপনার কলেজ, এক নজরে",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Navy
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    "প্রয়োজনীয় তথ্য সহজেই খুঁজে নিন।",
                    fontSize = 13.sp,
                    color = Muted
                )
            }
        }

        item {
            Column(
                Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    QuickCard(
                        "নোটিশ",
                        Icons.Filled.Notifications,
                        Modifier.weight(1f)
                    ) { onNavigate(1) }

                    QuickCard(
                        "একাডেমিক",
                        Icons.Filled.School,
                        Modifier.weight(1f)
                    ) { onNavigate(2) }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    QuickCard(
                        "ক্লাস রুটিন",
                        Icons.Filled.CalendarMonth,
                        Modifier.weight(1f)
                    ) { onNavigate(3) }

                    QuickCard(
                        "আরও সেবা",
                        Icons.Filled.GridView,
                        Modifier.weight(1f)
                    ) { onNavigate(4) }
                }
            }
        }

        item {
            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(
                    "গুরুত্বপূর্ণ তথ্য",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Navy
                )
                Spacer(Modifier.height(10.dp))

                InfoCard(
                    "নোটিশ বোর্ড",
                    "কলেজের ঘোষণা ও বিজ্ঞপ্তি",
                    Icons.Filled.Campaign
                ) { onNavigate(1) }

                Spacer(Modifier.height(9.dp))

                InfoCard(
                    "ফলাফল ও একাডেমিক",
                    "পরীক্ষা, বিভাগ ও বিষয়",
                    Icons.Filled.MenuBook
                ) { onNavigate(2) }

                Spacer(Modifier.height(9.dp))

                InfoCard(
                    "ক্লাস রুটিন",
                    "বর্ষ অনুযায়ী সময়সূচি",
                    Icons.Filled.CalendarMonth
                ) { onNavigate(3) }
            }
        }

        item {
            Text(
                "FULBARIYA COLLEGE • EST. 1972",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                color = Muted,
                fontSize = 10.sp
            )
        }
    }
}

@Composable
private fun QuickCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(17.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(Modifier.padding(15.dp)) {
            Icon(
                icon,
                contentDescription = null,
                tint = Navy,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                title,
                color = Navy,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun InfoCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Navy,
                modifier = Modifier.size(27.dp)
            )
            Spacer(Modifier.width(13.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    title,
                    color = Navy,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(4.dp))
                Text(subtitle, color = Muted, fontSize = 11.sp)
            }
            Icon(
                Icons.Filled.ChevronRight,
                contentDescription = null,
                tint = Gold
            )
        }
    }
}

@Composable
private fun ContentScreen(
    title: String,
    subtitle: String,
    entries: List<String>,
    icon: ImageVector
) {
    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Navy)
                .padding(20.dp)
        ) {
            Text(
                "FULBARIYA COLLEGE",
                color = Gold,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
            Spacer(Modifier.height(9.dp))
            Text(
                title,
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(5.dp))
            Text(subtitle, color = Color(0xFFDCE2F7), fontSize = 12.sp)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(entries) { entry ->
                InfoCard(
                    entry,
                    "প্রকৃত তথ্য সংযুক্ত হলে এখানে বিস্তারিত দেখানো হবে",
                    icon
                ) {}
            }

            item {
                Text(
                    "এগুলো নমুনা তথ্য। লাইভ তথ্যের জন্য অনুমোদিত ডেটাবেস/API যুক্ত করতে হবে।",
                    color = Muted,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}
